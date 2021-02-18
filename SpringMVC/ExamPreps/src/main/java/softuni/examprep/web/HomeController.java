package softuni.examprep.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.examprep.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService  productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null){
            return "index";
        }
        model.addAttribute("totalSum",productService.getTotalPrice());
        return "home";
    }
}
