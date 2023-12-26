package com.beom.controller;


import com.beom.common.enumerations.ErrorEnumerate;
import com.beom.common.exception.ApiException;
import com.beom.service.PayService;
import com.beom.service.SmartroService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@RequiredArgsConstructor
@Slf4j
@Controller
public class PayController {

    private final PayService payService;
    private final SmartroService smartroService;

    @GetMapping("/ready")
    public String ready(Model Model) {
        return "ready";
    }

    @GetMapping("/error2")
    @ResponseBody
    public void error2() {
        throw new ApiException(ErrorEnumerate.EXISTS_LOGINID);
    }

    @PostMapping("/pay")
    public String smatroPay(Model Model, @RequestParam("Mid") String Mid, @RequestParam("MerchantKey") String MerchantKey, @RequestParam("Amt") String Amt) {
        smartroService.smartroPay(Model, Mid, MerchantKey, Amt);
        return "pay";
    }

    @PostMapping("/return")
    @ResponseBody
    public String returnUrl2(@RequestParam("Tid") String Tid, @RequestParam("TrAuthKey") String TrAuthKey) {
        String url = "https://tapproval.smartropay.co.kr/payment/approval/urlCallApproval.do";        // 테스트
        HashMap<String, Object> result = smartroService.callApi(TrAuthKey, Tid, url);
        return result.toString();
    }

}
