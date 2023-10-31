package com.beom.pay.controller;



import com.beom.common.log.MyLogger;
import com.beom.pay.service.PayService;
import com.beom.pay.service.SmartroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.beom.common.util.SmartroUtil.*;

@RequiredArgsConstructor
@Slf4j
@Controller
public class PayController {

    private final PayService payService;
    private final SmartroService smartroService;
    private final MyLogger myLogger;

    @GetMapping("/ready")
    public String ready(Model Model) {
        myLogger.log("ready");
        myLogger.log("ready");
        return "ready";
    }

    @PostMapping("/pay")
    public String smatroPay(Model Model, @RequestParam("Mid") String Mid, @RequestParam("MerchantKey") String MerchantKey, @RequestParam("Amt") String Amt) {
        myLogger.log("pay");
        smartroService.smartroPay(Model, Mid, MerchantKey, Amt);
        return "pay";
    }

    @GetMapping("/return")
    public String returnUrl(@RequestParam("Tid")String Tid, @RequestParam("TrAuthKey") String TrAuthKey) {
        log.info(Tid+TrAuthKey+"GET");
        return "return";
    }
    @PostMapping("/return")
    public String returnUrl2(@RequestParam("Tid")String Tid, @RequestParam("TrAuthKey") String TrAuthKey) {
        log.info(Tid+TrAuthKey+"POST");
        return "return";
    }




}
