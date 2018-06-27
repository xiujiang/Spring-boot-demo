package com.dao;

import com.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by joel on 2018/6/27.
 */
public interface MemberInfoDao extends JpaRepository<MemberInfo,Long> {

}
