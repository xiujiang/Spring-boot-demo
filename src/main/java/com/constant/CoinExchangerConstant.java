package com.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易所
 */
public class CoinExchangerConstant {
    public static Map<String,String> maps = new HashMap<String,String>();
    public static List<String> lists = new ArrayList<String>();
    static{
        maps.put("coinPark","coinPark");
        maps.put("okex","");
    }

    public static Map<String, String> getMaps() {
        return maps;
    }

    public static void setMaps(Map<String, String> maps) {
        CoinExchangerConstant.maps = maps;
    }
}
