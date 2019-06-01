package com.controller;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import com.alibaba.fastjson.JSONObject;
import com.util.Account;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

/**
 * Account地址管理
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/6/1
 * @since 1.8
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    /**
     * 这个body 应该是一个json 数据
     * @param body
     */
    @RequestMapping(value = "/create",produces = "application/json",consumes = "application/json")
    public void createAccount(@RequestBody String body){
        if(StringUtils.isEmpty(body)){
            return;
        }
        JSONObject json = JSONObject.parseObject(body);
        if(json.isEmpty()){
            return;
        }
        Integer times = (Integer) json.get("times");
        if(times <= 0){
            return;
        }

        Account account = new Account();
        List<String> addressList = new ArrayList<>();
        try {
            for (int i = 0; i < times; i++){
                String address = account.createAccountByWeb3j("C:/ethAddress/","liuxiujiang");
                addressList.add(address);
            }
        } catch (Exception e) {

        }


    }
}
