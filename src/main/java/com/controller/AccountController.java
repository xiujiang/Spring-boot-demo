package com.controller;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import com.alibaba.fastjson.JSONObject;
import com.entity.AccountInfo;
import com.service.AccountInfoService;
import com.util.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    AccountInfoService accountService;
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

        List<String> addressList = new ArrayList<>();
        Account account = new Account();
        try {
            for (int i = 0; i < times; i++){
                String address = account.createAccountByWeb3j("C:/ethAddress/","liuxiujiang");
                System.out.println(address);
                addressList.add(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("saldijfalsdfj:{}",addressList.toString());
        for(String address:addressList){
            logger.info("dizhi:{}",address);
            System.out.println("adddisaf"+address);
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setAddress(address);
            accountInfo.setPassword("xx");
            accountInfo.setAddressName(address);
            accountService.save(accountInfo);
        }

    }

//    public static void main(String[] args) throws IOException {
//        File file = new File("C:/ethAddress/123.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        fileOutputStream.write("123".getBytes());
//        fileOutputStream.close();
//    }
}
