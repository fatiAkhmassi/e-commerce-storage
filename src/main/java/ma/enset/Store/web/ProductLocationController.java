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
public class ProductLocationController {


    private ProductLocationRepository productLocationRepository;
    private ProductRepository productRepository;
    private LocationRepository locationRepository;

    /*Productlocation*/
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
    public String saveProductsLocation(@Valid ProductLocation productLocation,Product product,Location location,BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formProductsLocation";
        /*Product product=productRepository.findById(productLocation.getProduct().getId()).get();
        Location location=locationRepository.findById(productLocation.getLocation().getId()).get();
        productLocation.setProduct(product);
        productLocation.setLocation(location);*/
        productLocationRepository.save(productLocation);
        return "redirect:/formProductsLocation";
    }


    /*@RequestMapping(value="/savefile",method= RequestMethod.POST)
    public ModelAndView upload(@RequestParam CommonsMultipartFile file, HttpSession session){
        String path=session.getServletContext().getRealPath("/");
        String filename=file.getOriginalFilename();

        System.out.println(path+" "+filename);
        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(path+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        }catch(Exception e){System.out.println(e);}
        return new ModelAndView("upload-success","filename",path+"/"+filename);
    }

    @GetMapping("/uploadFile")
    public String uploadFile(){
        return "uploadFile";
    }*/

}
