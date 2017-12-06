package com.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by joel in 2017/12/6.
 * 定时任务模板方法
 */
public abstract  class AbstractTask {
    Logger logger = LoggerFactory.getLogger(getClass());

    private String Key="KEY";

    public abstract  void execute();
}
