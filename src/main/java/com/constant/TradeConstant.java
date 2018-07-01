package com.constant;

import java.math.BigDecimal;

/**
 * Created by joel on 2018/1/13.
 */
public class TradeConstant {
    public static String api = "a948257f-1fd6-44a0-b187-37f96553a420";
    public static String symbol = "iost_usdt";        //交易币种
    public static BigDecimal defaultPrice = new BigDecimal(1);     //交易最低价
    public static BigDecimal highPrice = new BigDecimal(0.05);         //交易最高价
    public static String closeTradeSign = "false";                      //是否关闭交易

    public static String transSymbol = "iost_usdt,lrc_usdt";

    public final static String ORDER_STATUS_NO_DEAL = "NO_DEAL";           //未成交
    public final static String ORDER_STATUS_SETTLE = "SETTLE";              //已成交


}
