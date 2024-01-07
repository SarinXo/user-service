package com.example.userservice.services;

import com.example.userservice.entities.Pig;
import com.example.userservice.entities.PigForSale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PigForSaleService {
    void sell(PigForSale pigForSale);

    Page<PigForSale> findAll(Pageable pageable);

    List<PigForSale> getByFarmerId(Integer id);

    void takeBack(Integer integer);

    Page<PigForSale> findAllByKeywordAndSortWord(String keyWord,
                                                 Pageable pageable);
}
