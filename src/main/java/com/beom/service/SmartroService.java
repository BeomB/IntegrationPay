package com.beom.service;

import com.beom.common.enumerations.Company;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;

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
    public String ready() {
        return "SmatroService.ready";
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


    @Override
    public boolean supports(Company company)
    {
        return false;
    }

}
