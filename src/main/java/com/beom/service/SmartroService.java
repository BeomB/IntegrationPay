package com.beom.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.beom.common.util.SmartroUtil.*;

@Service
@Primary
@Slf4j
public class SmartroService implements PayService{


    @Override
    public String pay() {
        return "SmatroService.pay";
    }

    @Override
    public String cancel() {
        return "SmatroService.cancel";
    }

    @Override
    public String query() {
        return "SmatroService.query";
    }

    public void smartroPay(Model Model, String Mid, String MerchantKey, String Amt) {
        String EdiDate = getyyyyMMddHHmmss();
        String today = getyyyyMMddHHmm();        // 현재일자. 캐시방지용으로 사용
        String EncryptData = encodeSHA256Base64(EdiDate + Mid + Amt + MerchantKey);
        Model.addAttribute("Mid", Mid);
        Model.addAttribute("EncryptData", EncryptData);
        Model.addAttribute("MerchantKey", MerchantKey);
        Model.addAttribute("EdiDate", EdiDate);
        Model.addAttribute("Amt", Amt);
        log.info("MID : {} EncryptData:{} MerchantKey :{} EdiDate : {} ", Mid, EncryptData, MerchantKey, EdiDate);
    }

}
