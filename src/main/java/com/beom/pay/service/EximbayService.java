package com.beom.pay.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EximbayService implements PayService {

    @Override
    public String pay() {
        return "EximbayService.pay";
    }

    @Override
    public String cancel() {
        return "EximbayService.cancel";
    }

    @Override
    public String query() {
        return "EximbayService.query";
    }



}