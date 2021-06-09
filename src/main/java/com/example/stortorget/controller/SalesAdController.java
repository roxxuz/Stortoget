package com.example.stortorget.controller;

import com.example.stortorget.entity.SalesAd;
import com.example.stortorget.service.SalesAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class SalesAdController {

    @Autowired
    private SalesAdService salesAdService;

    @GetMapping("/")
    public String redirectIndex(){

        return "redirect:/ads";
    }

    @GetMapping("/ads")
    public String viewAds(Model model){

        model.addAttribute("ads", salesAdService.getAllAds());

        return "ads";
    }

    @GetMapping("/login")
    public String login(Principal principal){

        if (principal != null) {
            System.out.println(principal.getName());
        }

        return "login";
    }

    @GetMapping("/createads")
    public String createads(Model model, SalesAd salesAd){

        model.addAttribute("ads", salesAd);

        return "newad";
    }


    @GetMapping("/newad")
    public String newad(Model model, Principal principal, SalesAd salesAd){
    //    model.addAttribute("nad", salesAd);
    //    System.out.println(principal.getName());
    //    salesAd.setUserName(principal.getName());
        salesAdService.saveSalesAd(salesAd);


        return "redirect:/ads";
    }

    @GetMapping("/search_ad")
    public String searchAd(@RequestParam("search") String search,
                           @RequestParam("category") String category,
                           Model model){

        model.addAttribute("ads", salesAdService.searchAd(search, category));

        return "ads";
    }

}
