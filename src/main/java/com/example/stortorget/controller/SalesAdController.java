package com.example.stortorget.controller;

import com.example.stortorget.repository.SalesAdRepository;
import com.example.stortorget.service.SalesAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/create_ad")
    public String createAd(Model model){

        return "create_ad";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }
}
