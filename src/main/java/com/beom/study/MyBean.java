package com.beom.study;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    @PostConstruct
    public void postContructTest()
    {
        System.out.println("PostConstruct 가 실행되었씁니다!!");
    }

}
