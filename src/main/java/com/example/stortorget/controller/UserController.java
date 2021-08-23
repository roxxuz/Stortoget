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
        //Adding empty User object to model
        model.addAttribute("usser", user);
        return "createAccount";
    }

    @GetMapping("/createAcc")
    public String saveUser(@ModelAttribute("usr") User usr, Model model){


        userService.saveUser(usr);

        return "redirect:/login";
    }

    @GetMapping("/userSettings")
    public String userSettings(Model model, Principal principal) {

        //Adding User object of currently signed in user to the model
        model.addAttribute("currentUser", userService.getCurrentUser(principal));
        //Adding List of all ads created by the currently signed in user
        model.addAttribute("userAds", userService.getUserAds(principal));
        //Adding empty sales ad object
        model.addAttribute("emptyAd", new SalesAd());

        return "userSettings";
    }

    @PostMapping("/updateUser")
    public String updateUser(User currentUser, Principal principal) {
        //Updating the currently signed in user settings
        userService.editUser(currentUser, principal);
        return "redirect:/userSettings";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Principal principal){

        //Delete currently signed in user
        userService.deleteUser(principal);

        //Redirecting to main page after user is deleted
        return "redirect:/";
    }

    @GetMapping("/adminPanel")
    public String adminPanel(Model model) {

        model.addAttribute("user", new User());

        return "adminPanel";
    }

    @GetMapping("/accessDeniedPage")
    public String accessDeniedPage(Model model){

        model.addAttribute("denied", true);

        return "accessDenied";
    }


}
