package com.example.ShoppingList.web;

import com.example.ShoppingList.model.dto.ProductAddDTO;
import com.example.ShoppingList.model.service.ProductServiceModel;
import com.example.ShoppingList.services.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String addProduct(HttpSession httpSession){
        if(httpSession.getAttribute("user") == null){
            return "redirect:/login";
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid ProductAddDTO productAddDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddDTO", productAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddDTO",bindingResult);
            return "redirect:add";
        }


        productService.addProduct(modelMapper.map(productAddDTO, ProductServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute("productAddDTO")
    public ProductAddDTO productAdd(){
        return new ProductAddDTO();
    }

    @GetMapping("/buy/{id}")
    public String buyById(@PathVariable long id){
        productService.buyById(id);

        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll(){
        productService.buyAll();

        return "redirect:/";
    }
}
