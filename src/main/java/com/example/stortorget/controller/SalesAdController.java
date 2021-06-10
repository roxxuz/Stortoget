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

    @GetMapping("/createAd")
    public String createads(Model model, SalesAd salesAd, Principal principal){

        model.addAttribute("ads", salesAd);

        return "newAd";
    }


    @PostMapping("/newAd")
    public String newad(Principal principal, SalesAd salesAd){

        salesAd.setUserName(principal.getName());

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
