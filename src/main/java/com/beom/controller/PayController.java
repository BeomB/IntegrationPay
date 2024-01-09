package com.beom.controller;


import com.beom.common.enumerations.Company;
import com.beom.common.enumerations.ErrorEnumerate;
import com.beom.common.exception.ApiException;
import com.beom.service.PayService;
import com.beom.service.ServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

import static com.beom.common.util.SmartroUtil.callApi;

@RequiredArgsConstructor
@Slf4j
@Controller
public class PayController {

    private final ServiceFactory serviceFactory;


    @GetMapping("/ready")
    @ResponseBody
    public String ready(Model Model) {
        Company company = Company.SMARTRO;
        PayService payService = serviceFactory.find(company);
        return payService.ready();
    }

    @GetMapping("/error2")
    @ResponseBody
    public void error2() {
        throw new ApiException(ErrorEnumerate.EXISTS_LOGINID);
    }

}
