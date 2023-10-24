package com.beom.pay.service;

public interface PayService {


    //결제
    public String pay();

    //결제 취소
    public String cancel();

    //결제 조회
    public String query();



}
