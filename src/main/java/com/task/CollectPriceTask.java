package com.task;

import com.alibaba.fastjson.JSONObject;
import com.bean.OrderInfoBean;
import com.constant.TradeConstant;
import com.dao.CoinPriceDao;
import com.entity.CoinPrice;
import com.service.TradeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 *
 * 统计每秒ok的币价，币交易量，分析交易额
 * Created by joel on 2018/2/26.
 */
@Component
public class CollectPriceTask extends AbstractTask{
    @Resource
    TradeService tradeService;

    @Resource
    CoinPriceDao coinPriceDao;
    private static final OrderInfoBean defaultOrderInfoBean = new OrderInfoBean(TradeConstant.api, TradeConstant.symbol,"-1");
//    @Scheduled(cron = "0/2 * * * * ? ")
    @Override
    public void execute() throws Exception {
        System.out.println("更新价格.........");
        String [] coinSymbols = TradeConstant.transSymbol.split(",");
        if(coinSymbols.length > 0){
            for(int i = 0;i < coinSymbols.length; i++){
                try{
                    Map<String,String> tickerMap = tradeService.ticker(coinSymbols[i]);
                    String ticker = tickerMap.get("ticker");
                    JSONObject tickerJson = JSONObject.parseObject(ticker);
                    CoinPrice coinPrice = new CoinPrice();
                    coinPrice.setCoinSymbol(coinSymbols[i]);
                    coinPrice.setBuyOnePrice(tickerJson.get("buy").toString());
                    coinPrice.setCoinAmount(tickerJson.get("vol").toString());
                    coinPrice.setSellOnePrice(tickerJson.get("sell").toString());
                    coinPrice.setCoinPrice(new BigDecimal(tickerJson.get("last").toString()));
                    coinPrice.setCreateTime(new Date());
                    coinPrice.setLastUpdateTime(new Date());
                    //save
                    coinPriceDao.save(coinPrice);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
