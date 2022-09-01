package ma.enset.Store.web;

import lombok.AllArgsConstructor;
import ma.enset.Store.entities.Product;
import ma.enset.Store.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private ProductRepository productRepository;

    @GetMapping(path = "/")
    private String home(){
        return "index";
    }

    @GetMapping("/products")
    private String products(Model model,
                            @RequestParam(name="page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "3") int size){
        Page<Product> products = productRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("ListProducts",products.getContent());
        model.addAttribute("pages",new int[products.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        return "products";
    }




















    @GetMapping("/locations")
    private String locations(){
        return "locations";
    }
}
