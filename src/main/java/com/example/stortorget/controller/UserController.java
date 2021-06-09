package com.example.stortorget.controller;

import com.example.stortorget.entity.User;
import com.example.stortorget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/createaccount")
    public String createAccount(Model model, User user){
        model.addAttribute("usser", user);
        return "createaccount";
    }

    @GetMapping("/createAcc")
    public String saveUser(@ModelAttribute("usr") User usr, Model model){

    //    System.out.println("INNE I SAVEUSER");
        userService.saveUser(usr);

        return "redirect:/login";
    }

}
