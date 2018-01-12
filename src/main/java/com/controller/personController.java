package com.controller;

import com.entity.Person;
import com.service.PersonService;
import com.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by joel on 2017/8/10.
 */
@RestController
@RequestMapping("/person")
public class personController {
    @Autowired
    PersonService personService;

    @Resource
    TradeService tradeService;

    @RequestMapping("/findAllPerson")
    public void findAll(){
        List<Person> personList = personService.findAll();
        for (int i = 0; i <personList.size(); i++) {
            System.out.println(personList.get(i).toString());
        }
    }

    @RequestMapping("/ByName/{name}")
    public void findPersonByName(@PathVariable String name){
        System.out.println("Name:"+name);
        System.out.println(personService.findPersonByName(name).toString());
    }

    @RequestMapping("/findAll")
    public void findAllPerson(){
        List<Person> personList = personService.findAllPerson();
        for (int i = 0; i < personList.size(); i++) {
            System.out.println(personList.get(i).toString());
        }
    }

    @RequestMapping("futureTrades")
    @ResponseBody
    public String futureTrades(String symbol,String cont) throws Exception {
         String resp  = tradeService.futureTrades(symbol,cont);
         return resp;
    }
}
