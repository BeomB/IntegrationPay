package com.beom.service;

import com.beom.common.enumerations.Company;
import com.beom.repository.MemberMemoryRepository;
import com.beom.repository.MemberRepository;
import com.beom.repository.TransactionMemoryRepository;
import com.beom.study.MyBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EximbayService implements PayService {

    private final MemberMemoryRepository memberMemoryRepository;
    private final TransactionMemoryRepository transactionMemoryRepository;


//    private final MemberRepository memberRepository;

//    public EximbayService(@Qualifier("MemberMemoryRepository") MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

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