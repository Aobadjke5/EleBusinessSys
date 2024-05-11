package com.zyy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TestController {

    @RequestMapping("/test")
    public ArrayList<String> test() {
        ArrayList<String> ret = new ArrayList<>();
        ret.add("hello");
        ret.add("world");
        return ret;
    }
}
