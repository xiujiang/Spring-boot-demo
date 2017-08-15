package com.dao;

import com.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by joel on 2017/8/10.
 */
public interface PersonDao extends JpaRepository<Person, Long> {

    @Query(value="select * from person where name =?1",nativeQuery = true)
    Person findByName(String Name);

    @Query(value="select * from person",nativeQuery = true)
    List<Person> findAllPerson();
}
