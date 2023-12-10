//package com.beom.service;
//
//
//import com.beom.domain.Member;
//import com.beom.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class TestService {
//
//    private final MemberRepository memberRepository;
//
//    @Transactional
//    public Member  create(Member member)
//    {
//        member.setName("이범기");
//        memberRepository.save(member);
//        return member;
//    }
//
//    @Transactional
//    public Member read(String userId)
//    {
//        Member member = memberRepository.findByUserId(userId);
//        return member;
//    }
//
//    @Transactional
//    public Member update(String userId, Member member)
//    {
//        Member targetMember = memberRepository.findByUserId(userId);
//        log.info(targetMember.toString());
//
//        targetMember.setName(member.getName());
//        return targetMember;
//    }
//
//
//    @Transactional
//    public Member delete(String userId) {
//        Member deleteMember = memberRepository.findByUserId(userId);
//        memberRepository.delete(deleteMember);
//        return deleteMember;
//    }
//}
