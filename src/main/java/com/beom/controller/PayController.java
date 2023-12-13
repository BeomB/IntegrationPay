package com.beom.controller;


import com.beom.common.log.MyLogger;
import com.beom.service.PayService;
import com.beom.service.SmartroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;

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

    @PostMapping("/return")
    @ResponseBody
    public String returnUrl2(@RequestParam("Tid") String Tid, @RequestParam("TrAuthKey") String TrAuthKey) {
        String url = "https://tapproval.smartropay.co.kr/payment/approval/urlCallApproval.do";        // 테스트
        HashMap<String, Object> result = callApi(TrAuthKey, Tid, url);
        return result.toString();
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
