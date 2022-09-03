package ma.enset.Store.web;

import lombok.AllArgsConstructor;
import ma.enset.Store.entities.Location;
import ma.enset.Store.entities.Product;
import ma.enset.Store.entities.ProductLocation;
import ma.enset.Store.repositories.LocationRepository;
import ma.enset.Store.repositories.ProductLocationRepository;
import ma.enset.Store.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private ProductRepository productRepository;
    private LocationRepository locationRepository;
    private ProductLocationRepository productLocationRepository;


    /*products*/
    @GetMapping(path = "/")
    public String home(){
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model,
                            @RequestParam(name="page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "3") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Product> products = productRepository.findByLabelContainsOrRefContains(keyword,keyword,PageRequest.of(page,size));
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
    public String deleteProduct(Long id,int page,int size,String keyword){
        productRepository.deleteById(id);
        return "redirect:/products?page="+page+"&size="+size+"&keyword="+keyword;
    }

    @PostMapping("/editProductInfo")
    public String editProductInfo(@Valid Product product, BindingResult bindingResult,int page,int size,String keyword){
        if(bindingResult.hasErrors()) return "editProduct";
        productRepository.save(product);
        return "redirect:/products?page="+page+"&size="+size+"&keyword="+keyword;
    }

    @GetMapping("/formProducts")
    public String formProducts(Model model){
        model.addAttribute("product",new Product());
        return "formProducts";
    }

    @PostMapping("/saveProducts")
    public String saveProducts(@Valid Product product,BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formProducts";
        productRepository.save(product);
        return "redirect:/formProducts";
    }








    /*locations*/
    @GetMapping("/locations")
    public String locations(Model model,
                            @RequestParam(name="page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "4") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Location> locations = locationRepository.findByAdressContains(keyword,PageRequest.of(page,size));
        model.addAttribute("ListLocations",locations.getContent());
        model.addAttribute("pages",new int[locations.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("keyword",keyword);
        return "locations";
    }

    @GetMapping("/deleteLocation")
    public String deleteLocation(Long id,int page,int size,String keyword){
        locationRepository.deleteById(id);
        return "redirect:/locations?page="+page+"&size="+size+"&keyword="+keyword;
    }

    @GetMapping("/editLocation")
    public String editLocation(Model model,Long id,int page,int size,String keyword){
        Location location=locationRepository.findById(id).orElse(null);
        if (location==null) throw  new RuntimeException("Product not found");
        model.addAttribute("location",location);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("keyword",keyword);
        return "editLocation";
    }

    @PostMapping("/editLocationInfo")
    public String editLocationInfo(@Valid Location location, BindingResult bindingResult,int page,int size,String keyword){
        if(bindingResult.hasErrors()) return "editProduct";
        locationRepository.save(location);
        return "redirect:/locations?page="+page+"&size="+size+"&keyword="+keyword;
    }

    @GetMapping("/formLocations")
    public String formLocations(Model model){
        model.addAttribute("location",new Location());
        return "formLocations";
    }

    @PostMapping("/saveLocations")
    public String saveLocations(@Valid Location location,BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formLocations";
        locationRepository.save(location);
        return "redirect:/formLocations";
    }














    /*Productlocation*/
    @GetMapping("/formProductsLocation")
    public String formProductsLocation(Model model){
        List<Product> listProduct = productRepository.findAll();
        List<Location> listLocation = locationRepository.findAll();
        model.addAttribute("productLocation",new ProductLocation());
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("listLocation",listLocation);
        return "formProductsLocation";
    }

    @PostMapping("/saveProductsLocation")
    public String saveProductsLocation(@Valid ProductLocation productLocation,BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formProductsLocation";
        productLocationRepository.save(productLocation);
        return "redirect:/formProductsLocation";
    }

    @GetMapping("/productionsLocations")
    public String productionsLocations(Model model,
                                       @RequestParam(name = "page",defaultValue = "0") int page,
                                       @RequestParam(name = "size",defaultValue = "4") int size,
                                       @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<ProductLocation> productLocations=productLocationRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listProductLocations",productLocations);
        model.addAttribute("pages",new int[productLocations.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("keyword",keyword);
        return "productionsLocations";
    }
}
