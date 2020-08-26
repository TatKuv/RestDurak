package com.durak.durakapp.main;

import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Default {
    @RequestMapping("/date")
    
    public String date() {
        return (new Date()).toString();
    }

    @RequestMapping("/ok")

    public String ok() {
        return ("Hello World");
    }
}