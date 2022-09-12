package ma.enset.Store.web;

import lombok.AllArgsConstructor;
import ma.enset.Store.entities.Location;
import ma.enset.Store.entities.Product;
import ma.enset.Store.entities.ProductLocation;
import ma.enset.Store.entities.ProductLocationId;
import ma.enset.Store.repositories.LocationRepository;
import ma.enset.Store.repositories.ProductLocationRepository;
import ma.enset.Store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductRepository productRepository;
    private LocationRepository locationRepository;
    private ProductLocationRepository productLocationRepository;
    private final Path rootLocation=Paths.get("upload-dir");
    @Autowired
    public void ProductFileCreate() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*products*/
    @GetMapping(path = "/")
    public String home(){
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model,
                            @RequestParam(name="page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "8") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Product> products = productRepository.findByLabelContainsOrRefContains(keyword,keyword,PageRequest.of(page,size));
        if (products==null) return "index";
        model.addAttribute("ListProducts",products.getContent());
        model.addAttribute("pages",new int[products.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("keyword",keyword);
        return "products";
    }

    @GetMapping("/editProduct")
    public String editProduct(Model model,Long id,int page,int size,String keyword){
        Product product=productRepository.findById(id).orElse(null);
        if (product==null) throw  new RuntimeException("Product not found");
        model.addAttribute("product",product);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("keyword",keyword);
        return "editProduct";
    }

    @GetMapping("/deleteProduct")
    public ModelAndView deleteProduct(ModelMap model, Long id, int page, int size, String keyword){
        Product product=productRepository.findById(id).orElse(null);
        List<ProductLocation> productLocations=productLocationRepository.findByPrimaryKeyProduct(product);
        if(productLocations.size()!=0){
            String error="You can not delete this product " + product.getRef() + ", this product is already affected to at least one location.";
            model.addAttribute("error",error);
            return new ModelAndView("forward:/productionsLocations",model);
        }
        productRepository.deleteById(id);
        return  new ModelAndView("redirect:/products?page="+page+"&size="+size+"&keyword="+keyword,model);
    }

    @PostMapping("/editProductInfo")
    public String editProductInfo(@Valid Product product,MultipartFile productImageFile, BindingResult bindingResult,int page,int size,String keyword){
        if(bindingResult.hasErrors()) return "editProduct";
        try {
            if (productImageFile!=null) {
                final String imagePath = "upload-dir/"; //path
                FileOutputStream output = new FileOutputStream(imagePath + productImageFile.getOriginalFilename());
                output.write(productImageFile.getBytes());
                product.setProductImage(productImageFile.getOriginalFilename());
            }
            Product p=productRepository.findById(product.getId()).orElse(null);
            product.setProductImage(p.getProductImage());
            productRepository.save(product);

            return "redirect:/products?page="+page+"&size="+size+"&keyword="+keyword;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    @GetMapping("/formProducts")
    public String formProducts(Model model){
        model.addAttribute("product",new Product());
        return "formProducts";
    }

    @PostMapping("/saveProducts")
    public String saveProducts(@Valid Product product,MultipartFile productImageFile,BindingResult bindingResult) throws IOException {
        try {
            final String imagePath = "upload-dir/"; //path
            FileOutputStream output = new FileOutputStream(imagePath+productImageFile.getOriginalFilename());
            output.write(productImageFile.getBytes());
            // save product to db
            product.setProductImage(productImageFile.getOriginalFilename());
            productRepository.save(product);

            return "redirect:/formProducts";
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Path fpath = rootLocation.resolve(filename);
        try {
            Resource file = new UrlResource(fpath.toUri());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/locationsOfProduct")
    public String locationsOfProduct(Model model,Long id,
                           @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "8") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Product product=productRepository.findById(id).orElse(null);
        Page<ProductLocation> productLocations = productLocationRepository.findByPrimaryKeyProduct(product,PageRequest.of(page,size));
        model.addAttribute("product",product);
        model.addAttribute("locations",productLocations.getContent());
        model.addAttribute("pages",new int[productLocations.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("keyword",keyword);
        return "locationsOfProduct";
    }

    @GetMapping("/deleteLocationOfProduct")
    public String deleteProductLocation(Long productId,long locationId,int page,int size,String keyword){
        Product product=productRepository.findById(productId).orElse(null);
        Location location=locationRepository.findById(locationId).orElse(null);
        ProductLocationId productLocationId=new ProductLocationId(product,location);
        productLocationRepository.deleteById(productLocationId);
        return "redirect:/locationsOfProduct?id="+productId+"&page="+page+"&size="+size+"&keyword="+keyword;
    }
}




