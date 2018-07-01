package com.constant;

import com.bean.TradeInfoBean;
import com.bean.UserTradeInfoBean;
import com.entity.Member;
import com.entity.MemberInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 会员
 */
public class MemberConstant {

    //默认等级
    public static  String DEFAULT_LEVEL = "0";

    public static Map<String,TradeInfoBean> tradeInfoBeanHashMap = new HashMap<>();


    public static Map<String,UserTradeInfoBean> userTradeInfoBeanMap = new HashMap<>();

    public static Map<String, TradeInfoBean> getTradeInfoBeanHashMap() {
        return tradeInfoBeanHashMap;
    }

    public static synchronized void  setTradeInfoBeanHashMap(Map<String, TradeInfoBean> tradeInfoBeanHashMap) {
        MemberConstant.tradeInfoBeanHashMap = tradeInfoBeanHashMap;
    }

    public static Map<String, UserTradeInfoBean> getUserTradeInfoBeanMap() {
        return userTradeInfoBeanMap;
    }

    public static synchronized void setUserTradeInfoBeanMap(Map<String, UserTradeInfoBean> userTradeInfoBeanMap) {
        MemberConstant.userTradeInfoBeanMap = userTradeInfoBeanMap;
    }
}


