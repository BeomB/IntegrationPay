package com.beom.pay.controller;

import com.beom.pay.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    private final PayService payService;

    public TestController(@Qualifier("eximbayService") PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/test/pay")
    public void testPay()
    {
        System.out.println(payService.pay());
    }
    @GetMapping("/test/query")
    public void testQuery()
    {
        System.out.println(payService.query());
    }

    @GetMapping("/test/cancel")
    public void testCancel()
    {
        System.out.println(payService.cancel());
    }
}
