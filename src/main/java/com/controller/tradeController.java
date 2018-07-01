package com.controller;

import com.bean.TradeInfoBean;
import com.bean.UserTradeInfoBean;
import com.constant.MemberConstant;
import com.constant.TradeConstant;
import com.dao.MemberDao;
import com.entity.Member;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by joel on 2018/2/25.
 */
@RestController
@RequestMapping("/trade")
public class tradeController {

    @Resource
    MemberDao memberDao;

    @RequestMapping("closeTrade")
    public void closeTrade(){
        TradeConstant.closeTradeSign = "true";
    }

    @RequestMapping("startTrade")
    public void startTrade(){
        TradeConstant.closeTradeSign = "false";
    }

    @RequestMapping("modifyCoin")
    public void modifyCoin(String symbol){
        TradeConstant.symbol = symbol;
    }

    @RequestMapping("addSymbol")
    public void addSymbol(String symbol){
        TradeConstant.transSymbol += symbol;
    }

    @RequestMapping("addMember")
    public void addMember(String merNo,String apiKey,String sercet,String amount,String coinRate,String symbol,String coinExchange,String times,String allAmount){
        System.out.println(MemberConstant.getTradeInfoBeanHashMap());
        System.out.println(MemberConstant.getUserTradeInfoBeanMap());
        Member member = memberDao.findByMerNo(merNo);
        if(member == null){
            return;
        }
       Map<String,TradeInfoBean> maps =  MemberConstant.getTradeInfoBeanHashMap();
        TradeInfoBean tradeInfoBean = new TradeInfoBean();
        tradeInfoBean.setApiKey(apiKey);
        tradeInfoBean.setSercet(sercet);
        tradeInfoBean.setMerNo(merNo);
        tradeInfoBean.setAmount(new BigDecimal(amount));
        tradeInfoBean.setBuyAndSell("1");
        tradeInfoBean.setSymbol(symbol);
        tradeInfoBean.setCoinChange(coinExchange);
        maps.put(merNo,tradeInfoBean);
        Map<String,UserTradeInfoBean> userTradeInfoBeanMap = MemberConstant.getUserTradeInfoBeanMap();
        UserTradeInfoBean userTradeInfoBean = new UserTradeInfoBean();
        userTradeInfoBean.setMerNo(merNo);
        userTradeInfoBean.setBuyOrSell(tradeInfoBean.getBuyAndSell());
        userTradeInfoBean.setNowAccount(new BigDecimal(0));
        userTradeInfoBean.setAccount(new BigDecimal(allAmount));
        userTradeInfoBean.setNowTimes("0");
        userTradeInfoBean.setTimes(times);
        userTradeInfoBean.setTradeRate(coinRate);
        userTradeInfoBeanMap.put(merNo,userTradeInfoBean);

        System.out.println(MemberConstant.getTradeInfoBeanHashMap());
        System.out.println(MemberConstant.getUserTradeInfoBeanMap());
    }

}
