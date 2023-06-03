package com.example.ShoppingList.web;

import com.example.ShoppingList.model.enums.CategoryNames;
import com.example.ShoppingList.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    private String index(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("user") == null){
            return "index";
        }
        model.addAttribute("totalSum",productService.totalSum());
        model.addAttribute("drinks", productService.findAllProductsByCategoryName(CategoryNames.Drink));
        model.addAttribute("foods", productService.findAllProductsByCategoryName(CategoryNames.Food));
        model.addAttribute("houseHolders", productService.findAllProductsByCategoryName(CategoryNames.Household));
        model.addAttribute("others", productService.findAllProductsByCategoryName(CategoryNames.Other));
        return "home";
    }


}
