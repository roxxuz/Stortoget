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

    //Redirect to show all ads as the first landing page.
    @GetMapping("/")
    public String redirectIndex(){

        return "redirect:/ads";
    }

    @GetMapping("/ads")
    public String viewAds(Model model){

        //Adds a List of all ads from the DB to the model
        //This list is then used with Thymeleaf loop in ads.html
        model.addAttribute("ads", salesAdService.getAllAds());

        return "ads";
    }

    @GetMapping("/login")
    public String login(Principal principal){

        //Renders login.html

        return "login";
    }

    @GetMapping("/createAd")
    public String createads(Model model, SalesAd salesAd, Principal principal){

        //Renders newAd.html with an empty salesAd object called "ads"
        model.addAttribute("ads", salesAd);

        return "newAd";
    }


    @PostMapping("/newAd")
    public String newad(Principal principal, SalesAd salesAd){

        //Sets the currently logged in user as username for the created ad.
        salesAd.setUserName(principal.getName());

        //saves new ad to DB
        salesAdService.saveSalesAd(salesAd);

        //redirects back to /ads (method viewAds)
        return "redirect:/ads";
    }

    //RequestParam will get the search string and category from the url
    // ex. ( /search_ad?search=Pandas&category=Books ) will be String search = "Pandas"; and String category = "Books";
    @GetMapping("/search_ad")
    public String searchAd(@RequestParam("search") String search,
                           @RequestParam("category") String category,
                           Model model){

        //Sending search string and category to searchAd method.
        //Adding the returned ads List to the model
        model.addAttribute("ads", salesAdService.searchAd(search, category));

        //Render ads.html together with the added ads List
        return "ads";
    }

    //Delete message based on the id in the url
    @GetMapping("/delete_message/{id}")
    public String deleteMessage(@PathVariable long id){

        salesAdService.deleteAd(id);

        return "redirect:/userSettings";
    }

}
