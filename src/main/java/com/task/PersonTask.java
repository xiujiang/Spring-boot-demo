package com.task;

import com.entity.Person;
import com.service.PersonService;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by joel on 2017/12/6.
 * 定时任务模板方法
 */
//@Component
public class PersonTask extends AbstractTask {
    @Resource
    private PersonService personService;

    @Scheduled(cron = "0/5 * * * * ? ")
    @Override
    public void execute() {
        logger.info("执行任务");
        List<Person> personList = personService.findAllPerson();
        logger.info("当前注册人数为:{}",personList.size());
    }
}
