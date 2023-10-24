package com.beom.pay.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Primary
public class SmatroService implements PayService{


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
}
