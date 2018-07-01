package com.dao;

import com.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by joel on 2018/6/27.
 */
public interface MemberInfoDao extends JpaRepository<MemberInfo,Long> {

    @Query(value="select * from member_info where mer_no = ?1",nativeQuery = true)
    public MemberInfo findByMemberInfoByMerNo(String merNo);

    @Query(value="select * from member_info where mer_no = ?1 and coin_exchanger = ?2",nativeQuery = true)
    public MemberInfo findByMemberInfoByMerNo(String merNo,String coinExchanger);

}
