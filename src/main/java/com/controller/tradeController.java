package com.controller;

import com.constant.TradeConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joel on 2018/2/25.
 */
@RestController
@RequestMapping("/trade")
public class tradeController {

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
}
