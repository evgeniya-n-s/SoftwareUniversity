package com.example.Coffee.controller;

import com.example.Coffee.model.dto.AddOrderDTO;
import com.example.Coffee.model.service.OrderServiceModel;
import com.example.Coffee.service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String order(){
        return "order-add";
    }

    @PostMapping("/add")
    public String order(@Valid AddOrderDTO addOrderDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOrderDTO", addOrderDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderDTO",
                    bindingResult);

            return "redirect:add";
        }

        //add to db
        orderService.addOrder(modelMapper.map(addOrderDTO, OrderServiceModel.class));


        return "redirect:/";
    }

    @ModelAttribute("addOrderDTO")
    public AddOrderDTO unitAddOrderDTO(){
        return new AddOrderDTO();
    }

    @GetMapping("/ready/{id}")
    public String ordersReady(@PathVariable Long id){
        orderService.readyOrder(id);

        return "redirect:/";
    }
}
