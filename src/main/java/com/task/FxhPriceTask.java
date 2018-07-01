package com.task;

import com.service.FxhCoinInfoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by joel on 2018/4/16.
 */
//@Component
public class FxhPriceTask extends AbstractTask{
    @Resource
    FxhCoinInfoService fxhCoinInfoService;


    @Scheduled(cron = "0 0 2,4,6,8,10,12,14,16,18,20,22 * * ? ")
    @Override
    public void execute() throws Exception {
        fxhCoinInfoService.getFxhCoinInfoByNet();
    }
}
