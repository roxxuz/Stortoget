package com.example.stortorget.service;

import com.example.stortorget.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesAdService {

    @Autowired
    private SalesAdRepository salesAdRepository;
    

}
