package com.beom.service;

import com.beom.common.enumerations.Company;
import com.beom.study.MyBean;
import lombok.RequiredArgsConstructor;
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
    public String ready() {
        return "EximbayService.ready";
    }

    @Override
    public String cancel() {
        return "EximbayService.cancel";
    }

    @Override
    public String query() {
        return "EximbayService.query";
    }

    @Override
    public boolean supports(final Company company)
    {
        return company == Company.EXIMBAY;
    }



}