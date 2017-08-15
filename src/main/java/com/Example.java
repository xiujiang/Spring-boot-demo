package com;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joel on 2017/8/9.
 */
@RestController
@EnableAutoConfiguration
public class Example {
    @RequestMapping("/")
    String home(){
        return "hello world!";
    }

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name){
        return "hello"+name+"!";
    }
}

