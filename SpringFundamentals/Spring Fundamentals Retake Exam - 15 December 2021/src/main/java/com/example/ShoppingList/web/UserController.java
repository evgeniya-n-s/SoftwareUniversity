package com.example.ShoppingList.web;

import com.example.ShoppingList.model.dto.UserLoginDTO;
import com.example.ShoppingList.model.dto.UserRegistrationDTO;
import com.example.ShoppingList.model.service.UserServiceModel;
import com.example.ShoppingList.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registration(){
        return "/register";
    }

    @PostMapping("register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegistrationDTO",userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO",bindingResult);
            return "redirect:register";
        }

        userService.register(modelMapper.map(userRegistrationDTO, UserServiceModel.class));
        return "redirect:login";
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO unitRegister(){
        return new UserRegistrationDTO();
    }

    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute("userLoginDTO")){
            model.addAttribute("userLoginDTO",new UserLoginDTO());
            model.addAttribute("notFound", false);
        }
        return "/login";
    }

    @PostMapping("login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        HttpSession httpSession){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginDTO",userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = userService.findByUsernameAndPassword(userLoginDTO.getUsername(),userLoginDTO.getPassword());

        if(userServiceModel==null){
            redirectAttributes.addFlashAttribute("userLoginDTO",userLoginDTO);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", userServiceModel);
        return "redirect:/";
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO userLoginDTO(){
        return new UserLoginDTO();
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
}
