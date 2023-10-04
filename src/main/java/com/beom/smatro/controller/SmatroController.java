package com.beom.smatro.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.beom.smatro.util.SmartroUtil.*;

@Controller
@Slf4j
public class SmatroController {


    @GetMapping("/ready")
    public String ready(Model Model) {
        return "ready";
    }

    @PostMapping("/pay")
    public String smatroPay(Model Model, @RequestParam("Mid") String Mid, @RequestParam("MerchantKey") String MerchantKey, @RequestParam("Amt") String Amt) {

        String EdiDate = getyyyyMMddHHmmss();
        String today = getyyyyMMddHHmm();        // 현재일자. 캐시방지용으로 사용
        String EncryptData = encodeSHA256Base64(EdiDate + Mid + Amt + MerchantKey);
        Model.addAttribute("Mid", Mid);
        Model.addAttribute("EncryptData", EncryptData);
        Model.addAttribute("MerchantKey", MerchantKey);
        Model.addAttribute("EdiDate", EdiDate);
        Model.addAttribute("Amt", Amt);
        log.info("MID : {} EncryptData:{} MerchantKey :{} EdiDate : {} ", Mid, EncryptData, MerchantKey, EdiDate);
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
