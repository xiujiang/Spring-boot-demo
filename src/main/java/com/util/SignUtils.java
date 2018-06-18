package com.util;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by joel on 2017/12/20.
 */
public class SignUtils {

    public static String getMaptoSign(JSONObject json, String key){
        List<String> lists = new ArrayList<String>(json.keySet());
        Collections.sort(lists);
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(json.toString());

        Iterator<String> it = lists.iterator();
        while(it.hasNext()){
            String name = it.next();
            if(null != json.get(name) && !"".equals(json.get(name))){
                stringBuffer.append(name).append("=").append(json.get(name)).append("&");
            }
        }
        stringBuffer.append("secret_key="+key);
        System.out.println(stringBuffer.toString());
        String md5 = DigestUtil.getMd5Str(stringBuffer.toString());
        return md5.toUpperCase();
    }

    public static String getMapstoSign(Map<String,Object> map, String key){
        List<String> lists = new ArrayList<String>(map.keySet());
        Collections.sort(lists);
        StringBuffer stringBuffer = new StringBuffer();

        Iterator<String> it = lists.iterator();
        while(it.hasNext()){
            String name = it.next();
            if(null != map.get(name) && !"".equals(map.get(name))){
                stringBuffer.append(name).append("=").append(map.get(name)).append("&");
            }
        }
        stringBuffer.append("secret_key="+key);
//        System.out.println(stringBuffer.toString());

        String md5 = DigestUtil.getMd5Str(stringBuffer.toString());
        return md5.toUpperCase();
    }
}
