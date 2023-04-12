package com.example.Coffee.controller;

import com.example.Coffee.model.view.OrderViewModel;
import com.example.Coffee.security.CurrentUser;
import com.example.Coffee.service.OrderService;
import com.example.Coffee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    private final OrderService orderService;

    private final UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model){
        if(currentUser.getId() == null){
            return "index";
        }

        List<OrderViewModel> orders = orderService.findAllOrdersByPriceDesc();


        model.addAttribute("orders",orders);
        model.addAttribute("totalTime", orders.stream().
                map(orderViewModel -> orderViewModel.getCategory().getNeededTime()).
                reduce((a,b)->a+b).orElse(0));

        model.addAttribute("users", userService.findAllUsersAndCountOfOrdersDesc());

        return "home";
    }
}
