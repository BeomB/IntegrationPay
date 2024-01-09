package com.beom.service;

import com.beom.common.enumerations.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceFactory {

    private final List<PayService> payServiceList;

    public PayService find(Company company)
    {
        return payServiceList.stream().filter(v->v.supports(company)).findFirst().orElseThrow();
    }

}
