package com.example.Coffee.service;

import com.example.Coffee.model.entity.Order;
import com.example.Coffee.model.service.OrderServiceModel;
import com.example.Coffee.model.view.OrderViewModel;
import com.example.Coffee.repository.OrderRepository;
import com.example.Coffee.security.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    private final UserService userService;

    private final CurrentUser currentUser;

    private final CategoryService categoryService;

    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository, UserService userService, CurrentUser currentUser, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }

    public void addOrder(OrderServiceModel orderServiceModel) {
        Order order = modelMapper.map(orderServiceModel,Order.class);
        order.setEmployee(userService.findById(currentUser.getId()));
        order.setCategory(categoryService.findByCategoryEnums(orderServiceModel.getCategory()));

        orderRepository.save(order);
    }

    public List<OrderViewModel> findAllOrdersByPriceDesc() {
        return orderRepository.findAllByOrderByPriceDesc().
                stream().map(order -> modelMapper.map(order, OrderViewModel.class)).collect(Collectors.toList());
    }

    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
