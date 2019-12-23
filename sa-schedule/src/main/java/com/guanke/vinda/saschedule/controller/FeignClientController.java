package com.guanke.vinda.saschedule.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignClientController {
    @RequestMapping("test")
    public String test() {
        return "is from sa-schedule";
    }
}
