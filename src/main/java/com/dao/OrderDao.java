package com.dao;

import com.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by joel on 2018/1/13.
 */
public interface OrderDao  extends JpaRepository<Order, Long> {
    @Query(value="select * from order_s",nativeQuery = true)
    List<Order> findAll(String Name);

    @Query(value="select * from order_s where type = ?1 ",nativeQuery = true)
    List<Order> findByType(String type);

    @Query(value="select * from order_s where status = ?1 and type = ?2 order by create_time desc",nativeQuery = true)
    List<Order> findByStatusAndType(String status,String type);

    @Query(value="select * from order_s where mer_no = ?1 and coin_exchanger=?2 and status = ?3",nativeQuery = true)
    List<Order> findByMerNoAndCoinExchangeAndStatus(String merNo,String CoinExchange,String status);

    @Query(value="select * from order_s where mer_no = ?1 and coin_exchanger = ?2 and type = ?3 order by create_time desc",nativeQuery = true)
    List<Order> findByMerNoAndCoinExchangeAndType(String merNo,String coinExchanger,String type);

}
