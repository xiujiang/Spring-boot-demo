package com;

import com.bean.OrderInfoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public interface CoinExchangerHandler {
    Logger logger =  LoggerFactory.getLogger(CoinExchangerHandler.class);

    /**
     * 获取全币种价格
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String,String> getAllCoinPrice(OrderInfoBean orderInfoBean) throws Exception;

    /**
     * 获取币种深度
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String,String> getCoinDepth(OrderInfoBean orderInfoBean) throws Exception;

    /**
     * 获取单一价格币种
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String, String> getSymbolCoinPrice(OrderInfoBean orderInfoBean) throws Exception;

    /**
     * 获取当前市场行情
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String, String> getSymbolTicker(OrderInfoBean orderInfoBean) throws Exception;

    /**
     * 获取用户资产信息
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String, String> getTransferAssets(OrderInfoBean orderInfoBean) throws Exception;

    /**
     * 下单交易
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String, String> trade(OrderInfoBean orderInfoBean) throws Exception;

    /**
     * 撤单
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String, String> cancelTrade(OrderInfoBean orderInfoBean) throws Exception;

    /**
     * 查询委托单
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String, String> orderpending(OrderInfoBean orderInfoBean) throws Exception;

    /**
     * 获取历史委托单
     * @param orderInfoBean
     * @return
     * @throws Exception
     */
    public Map<String, String> pendingHistoryList(OrderInfoBean orderInfoBean) throws Exception;
}
