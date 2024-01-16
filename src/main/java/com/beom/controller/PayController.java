package com.beom.controller;


import com.beom.common.enumerations.Company;
import com.beom.common.enumerations.ErrorEnumerate;
import com.beom.common.exception.ApiException;
import com.beom.service.CommonService;
import com.beom.service.PayService;
import com.beom.service.ServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.beom.common.util.SmartroUtil.callApi;

@RequiredArgsConstructor
@Slf4j
@Controller
public class PayController {

    private final ServiceFactory serviceFactory;
    private final CommonService commonService;

    @GetMapping("/ready")
    public String ready(Model model, @RequestParam("company") String company) {
        commonService.saveTransaction();
        Company companyType = Company.valueOf(company);
        PayService payService = serviceFactory.find(companyType);
        return "ready";
    }


    @GetMapping("/ready/{company}")
    @ResponseBody
    public ResponseEntity<String> ready(@PathVariable String company) {
            Company companyType = Company.valueOf(company);
            PayService payService = serviceFactory.find(companyType);

            return ResponseEntity.ok(payService.ready());
    }

    @GetMapping("/error2")
    @ResponseBody
    public void error2() {
        throw new ApiException(ErrorEnumerate.EXISTS_LOGINID);
    }
}
