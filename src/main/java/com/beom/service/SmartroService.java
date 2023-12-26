package com.beom.service;

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


    public HashMap<String, Object> callApi(String TrAuthKey, String Tid, String callUrl) {

        StringBuilder responseBody = null;
        HashMap<String, Object> result = new HashMap<>();

        // http urlCall 승인 요청 및 TrAuthKey 유효성 검증
        int connectTimeout = 1000;
        int readTimeout = 10000; // 가맹점에 맞게 TimeOut 조절

        URL url = null;
        HttpsURLConnection connection = null;

        try {

            SSLContext sslCtx = SSLContext.getInstance("TLSv1.2");
            sslCtx.init(null, null, new SecureRandom());

            url = new URL(callUrl);
            System.out.println(" url " + url.toString());
            connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sslCtx.getSocketFactory());

            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);

            OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(connection.getOutputStream()), "utf-8");

            JSONObject body = new JSONObject();
            body.put("Tid", Tid);
            body.put("TrAuthKey", TrAuthKey);

            char[] bytes = body.toString().toCharArray();
            osw.write(bytes, 0, bytes.length);
            osw.flush();
            osw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            responseBody = new StringBuilder();
            while ((line = br.readLine()) != null) {
                System.out.println(" response " + line);
                responseBody.append(line);
            }
            br.close();

            // 결제결과
            result = new ObjectMapper().readValue(responseBody.toString(), HashMap.class);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
