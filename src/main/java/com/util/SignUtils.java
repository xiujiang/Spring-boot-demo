package com.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
        System.out.println(stringBuffer.toString());
        return DigestUtil.getMd5Str(stringBuffer.toString());
    }

}
