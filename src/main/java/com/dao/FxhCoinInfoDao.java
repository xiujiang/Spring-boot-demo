package com.dao;

import com.entity.FxhCoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by joel on 2018/4/10.
 */
public interface FxhCoinInfoDao extends JpaRepository<FxhCoinInfo, Long> {
    @Query(value="select * from fxh_coin_info",nativeQuery = true)
    List<FxhCoinInfo> findCoinInfo();

    @Query(value="select * from fxh_coin_info where create between ?1 and ?2",nativeQuery = true)
    List<FxhCoinInfo> findCoinInfoByTime(Date startTime,Date endTime);
}
