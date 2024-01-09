package com.beom.repository;

import com.beom.domain.Member;
import com.beom.domain.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MemberMemoryRepository {


    private static List<Member> store = new ArrayList<>();


    public Member save(Member member)
    {
        store.add(member);
        return member;
    }

    public Member select(Integer Id)
    {
        Member member = store.get(Id);
        return member;
    }

}
