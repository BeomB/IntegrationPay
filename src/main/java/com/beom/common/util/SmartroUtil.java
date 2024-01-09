package com.beom.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SmartroUtil {
    /* SHA256 암호화 */
    public static final String encodeSHA256Base64(String strPW) {
        String passACL = null;
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            e.printStackTrace();
        }

        md.update(strPW.getBytes());
        byte[] raw = md.digest();
        byte[] encodedBytes = Base64.encodeBase64(raw);
        passACL = new String(encodedBytes);

        return passACL;
    }

    /* 현재일자 */
    public static final String getyyyyMMddHHmmss() {
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
        return yyyyMMddHHmmss.format(new Date());
    }

    /* 현재일자  */
    public static final String getyyyyMMddHHmm() {
        SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
        return yyyyMMddHHmm.format(new Date());
    }

    public static final HashMap<String, Object> callApi(String TrAuthKey, String Tid, String callUrl) {

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
