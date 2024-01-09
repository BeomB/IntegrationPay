package com.beom.service;

import com.beom.common.enumerations.Company;

public interface PayService {


    //결제
    public String pay();

    public String ready();

    //결제 취소
    public String cancel();

    //결제 조회
    public String query();

    boolean supports(Company company);



}
