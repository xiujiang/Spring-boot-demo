package com.controller;

import com.bean.OrderInfoBean;
import com.bean.TradeBean;
import com.dao.OrderDao;
import com.entity.Order;
import com.entity.Person;
import com.service.PersonService;
import com.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    @Resource
    OrderDao orderDao;

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

    @RequestMapping("/getOrder")
    public void findAllPerson(){
        List<Order> or = orderDao.findAll();
        System.out.println(or);

    }
    @RequestMapping("/getOrderName")
    public void findAllPerson12(){
        List<Order> or = orderDao.findByType("buy");
        System.out.println(or);

    }
    @RequestMapping("/SellOrBuyTrade")
    @ResponseBody
    public Map<String, String> SellOrBuyTrade(String apiKey,String symbol,String type,String price,String amount) throws Exception{
        TradeBean tradeBean = new TradeBean(apiKey,symbol,type,new BigDecimal(price),new BigDecimal(amount));
        Map<String, String> response = tradeService.SellOrBuyTrade(tradeBean);
        return response;
    }

    @RequestMapping("/ticker")
    @ResponseBody
    public Map<String, String> ticker(String symbol) throws Exception{
        Map<String, String> response = tradeService.ticker(symbol);
        return response;
    }

    @RequestMapping("/orderInfo")
    @ResponseBody
    public Map<String, String> orderInfo(String symbol,String orderId,String api) throws Exception{
        OrderInfoBean od = new OrderInfoBean(api,symbol,orderId);
        Map<String, String> response = tradeService.orderInfo(od);
        return response;
    }

}
