package com.service;

import com.dao.FxhCoinInfoDao;
import com.entity.FxhCoinInfo;
import com.util.ExcelUtil;
import com.util.HttpUtils2;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by joel on 2018/4/10.
 */
@Service
public class FxhCoinInfoService {
    @Resource
    FxhCoinInfoDao fxhCoinInfoDao;
    Logger logger = Logger.getLogger(getClass());
    public void saveAll(List<FxhCoinInfo> lists){
        fxhCoinInfoDao.save(lists);
    }

    public void getFxhCoinInfo(String file){
        try{
            logger.info("fileName: "+file);
            List<String[]> lists = ExcelUtil.getExcelAsFile(file);
            List<FxhCoinInfo> fxhList = new ArrayList<FxhCoinInfo>();
            int i = 0;
            for(String[] list:lists){
                i++;
                logger.info(list[0]+","+list[1]+","+list[2]+","+list[3]+","+list[4]+","+list[5]+","+list[6]+","+list[7]+",");
                FxhCoinInfo fxhCoinInfo = new FxhCoinInfo(list);
//                fxhCoinInfo.setId(i);
                fxhCoinInfo.setCreateTime(new Date());
                fxhCoinInfo.setLastUpdateTime(new Date());
                logger.info(fxhCoinInfo.toString());
                fxhCoinInfoDao.save(fxhCoinInfo);
            }
        }catch(Exception e){
            e.printStackTrace();

        }

    }

    public String getFxhCoinInfoByNet()  {
        String  s = "http://api.feixiaohao.com/coins/download/";
        String fileName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        try{
            byte[] bytes = HttpUtils2.postEntity(s,null);
            File file = new File(fileName+".xls");
            OutputStream out = new FileOutputStream(file);
            out.write(bytes);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        getFxhCoinInfo(fileName);
        return fileName;
    }

}
