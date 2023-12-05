package com.beom.pay.service;

import com.beom.pay.study.MyBean;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EximbayService implements PayService {

    private final MyBean myBean;

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