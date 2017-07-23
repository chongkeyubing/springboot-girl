package com.libaogang.controller;

import com.libaogang.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController     //responsebody+controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private GirlProperties girlProperties;     //注入配置类

    @GetMapping(value = "/say")             //get请求
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        return "id: " + myId;
//        return girlProperties.getCupSize();     //配置类使用
    }

    @GetMapping(value = "/say/{id}")
    public String say1(@PathVariable(value = "id", required = false) Integer myId) {
        return "id: " + myId;
//        return index.html;      //返回模板
    }

}
