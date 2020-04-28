package cn.tjhyyt.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @Value("${test.testName}")
    private String name;

    @GetMapping("/env")
    public String printEnv() {
        return name;
    }
}
