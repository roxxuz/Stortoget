package com.example.stortorget.repository;

import com.example.stortorget.entity.SalesAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesAdRepository extends JpaRepository<SalesAd, Long> {

    SalesAd findByUserName(String userName);
}
