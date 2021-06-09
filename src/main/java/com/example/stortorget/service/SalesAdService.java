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

    public List<SalesAd> searchAd(String search, String category) {

        if(category.equals("all")){

            return salesAdRepository.findByItemContainingOrDescriptionContaining(search, search);
        }
        else{
            
            return salesAdRepository.findByItemContainingAndCategoryOrDescriptionContainingAndCategory(search, category, search, category);
        }

    }

}
