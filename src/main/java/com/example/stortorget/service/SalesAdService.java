package com.example.stortorget.service;

import com.example.stortorget.entity.SalesAd;
import com.example.stortorget.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesAdService {

    @Autowired
    private SalesAdRepository salesAdRepository;


    public List<SalesAd> getAllAds(){
        return salesAdRepository.findAllByOrderByIdDesc();
    }

    public void saveSalesAd(SalesAd salesAd){
        salesAdRepository.save(salesAd);
    }

    //search method returning search on all categories or search on a specific category
    public List<SalesAd> searchAd(String search, String category) {

        //returning DB ads List from search on all categories
        if(category.equals("all")){

            return salesAdRepository.findByItemContainingOrDescriptionContainingOrderByIdDesc(search, search);
        }
        //returning DB ads List from search on specific category
        else{

            return salesAdRepository.findByItemContainingAndCategoryOrDescriptionContainingAndCategoryOrderByIdDesc(search, category, search, category);
        }

    }

    public void deleteAd(long id){

        salesAdRepository.deleteById(id);
    }

}
