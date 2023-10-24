package com.beom.common.util;

import jakarta.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

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





}
