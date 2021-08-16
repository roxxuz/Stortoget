package com.example.stortorget.repository;

import com.example.stortorget.entity.SalesAd;
import com.example.stortorget.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesAdRepository extends JpaRepository<SalesAd, Long> {

    SalesAd findById(long id);

    List<SalesAd> findByUserNameOrderByIdDesc(String userName);

    List<SalesAd> findAllByOrderByIdDesc();

    List<SalesAd> findByItemContainingOrDescriptionContainingOrderByIdDesc(String search1, String search2);

    List<SalesAd> findByItemContainingAndCategoryOrDescriptionContainingAndCategoryOrderByIdDesc(String search1,String category1, String search2, String category2);


}
