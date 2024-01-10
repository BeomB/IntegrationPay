package com.beom.repository;

import com.beom.domain.Member;
import com.beom.domain.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MemberMemoryRepository{




    private final static List<Member> memberStore = new ArrayList<>();


    public Member save(Member member)
    {
        memberStore.add(member);
        return member;
    }

    public Member select(Integer Id)
    {
        Member member = memberStore.get(Id);
        return member;
    }

}
