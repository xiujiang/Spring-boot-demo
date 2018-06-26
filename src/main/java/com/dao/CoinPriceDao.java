package com.dao;

import com.entity.CoinPrice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by joel on 2018/2/26.
 */
public interface CoinPriceDao extends JpaRepository<CoinPrice, Long> {

}
