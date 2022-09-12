package ma.enset.Store.web;

import lombok.AllArgsConstructor;
import ma.enset.Store.entities.Location;
import ma.enset.Store.entities.Product;
import ma.enset.Store.entities.ProductLocation;
import ma.enset.Store.entities.ProductLocationId;
import ma.enset.Store.repositories.LocationRepository;
import ma.enset.Store.repositories.ProductLocationRepository;
import ma.enset.Store.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class LocationController {

    private LocationRepository locationRepository;
    private ProductLocationRepository productLocationRepository;
    private ProductRepository productRepository;

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
    public ModelAndView deleteLocation(ModelMap model, Long id, int page, int size, String keyword){
        Location location=locationRepository.findById(id).orElse(null);
        List<ProductLocation> productLocations=productLocationRepository.findByPrimaryKeyLocation(location);
        if (productLocations.size()!=0){
            String error="You can not delete the Location "+location.getAdress()+", this location has Products";
            model.addAttribute("error",error);
            return new ModelAndView("forward:/productionsLocations",model);
        }
        locationRepository.deleteById(id);
        return new ModelAndView("redirect:/locations?page="+page+"&size="+size+"&keyword="+keyword);
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

    @GetMapping("/productsInLocation")
    public String productsInLocation(Model model, Long id,
                                     @RequestParam(name="page",defaultValue = "0") int page,
                                     @RequestParam(name = "size",defaultValue = "3") int size,
                                     @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Location location=locationRepository.findById(id).orElse(null);
        Page <ProductLocation> productLocations = productLocationRepository.findByPrimaryKeyLocation(location,PageRequest.of(page,size));
        model.addAttribute("products",productLocations.getContent());
        model.addAttribute("pages",new int[productLocations.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("keyword",keyword);
        return "productsInLocation";
    }

    @GetMapping("/deleteproductInLocation")
    public String deleteProductLocation(Long productId,long locationId,int page,int size,String keyword){
        Product product=productRepository.findById(productId).orElse(null);
        Location location=locationRepository.findById(locationId).orElse(null);
        ProductLocationId productLocationId=new ProductLocationId(product,location);
        productLocationRepository.deleteById(productLocationId);
        return "redirect:/productsInLocation?id="+locationId+"&page="+page+"&size="+size+"&keyword="+keyword;
    }
}
