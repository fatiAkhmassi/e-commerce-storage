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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@AllArgsConstructor
public class LocationController {

    private LocationRepository locationRepository;

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


}
