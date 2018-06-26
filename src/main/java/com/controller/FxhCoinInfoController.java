package com.controller;

import com.service.FxhCoinInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by joel on 2018/4/10.
 */
@RestController
@RequestMapping("/fxhCoinInfo")
public class FxhCoinInfoController {
    @Resource
    FxhCoinInfoService fxhCoinInfoService;
    @RequestMapping("saveInfo")
    public void saveInfo(String fileName){
        fxhCoinInfoService.getFxhCoinInfo(fileName);
    }

    @RequestMapping("getInfo")
    public void getInfo(){
        String fileName = fxhCoinInfoService.getFxhCoinInfoByNet();
        System.out.println(fileName);
    }
}
