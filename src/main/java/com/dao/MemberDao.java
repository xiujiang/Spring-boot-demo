package com.dao;

import com.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by joel on 2018/6/27.
 */
public interface MemberDao extends JpaRepository<Member, Long> {
    @Query(value="select * from member",nativeQuery = true)
    List<Member> findAll(String Name);

    @Query(value="select * from member where mer_no = ?1",nativeQuery = true)
    Member findByMerNo(String merNo);

}
