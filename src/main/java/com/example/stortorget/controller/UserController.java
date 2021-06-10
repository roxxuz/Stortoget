package com.example.stortorget.controller;

import com.example.stortorget.entity.SalesAd;
import com.example.stortorget.entity.User;
import com.example.stortorget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/createAccount")
    public String createAccount(Model model, User user){
        model.addAttribute("usser", user);
        return "createAccount";
    }

    @GetMapping("/createAcc")
    public String saveUser(@ModelAttribute("usr") User usr, Model model){

    //    System.out.println("INNE I SAVEUSER");
        userService.saveUser(usr);

        return "redirect:/login";
    }

    @GetMapping("/userSettings")
    public String userSettings(Model model, Principal principal) {

        model.addAttribute("currentUser", userService.getCurrentUser(principal));

        model.addAttribute("userAds", userService.getUserAds(principal));

        model.addAttribute("emptyAd", new SalesAd());

        return "userSettings";
    }

    @PostMapping("/updateUser")
    public String updateUser(User currentUser, Principal principal) {
        System.out.println(currentUser);
        userService.editUser(currentUser, principal);
        return "redirect:/userSettings";
    }

}
