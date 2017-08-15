package com.service;

import com.dao.PersonDao;
import com.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joel on 2017/8/10.
 */
@Service
@Transactional
public class PersonService {
    @Autowired
    PersonDao personDao;

    public Person findPersonByName(String Name){
        return personDao.findByName(Name);
    }

    public List<Person> findAllPerson(){
        return personDao.findAllPerson();
    }

    public List<Person> findAll(){
        return personDao.findAll();
    }
}
