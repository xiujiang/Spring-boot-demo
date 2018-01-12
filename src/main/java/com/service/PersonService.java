package com.service;

import com.dao.PersonDao;
import com.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joel on 2017/8/10.
 * person Service
 */
@Service
@Transactional
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PersonDao personDao;
    public PersonService(){super();}
    public PersonService(PersonDao personDao){
        this.personDao = personDao;
    }

    public Person findPersonByName(String Name){
        logger.info("查询商户:{}",Name);
        return personDao.findByName(Name);
    }

    public List<Person> findAllPerson()
    {
        logger.info("查询所有商户");
        return personDao.findAllPerson();
    }

    public List<Person> findAll()
    {
        logger.info("查询所有信息FindAll");
        return personDao.findAll();
    }
}
