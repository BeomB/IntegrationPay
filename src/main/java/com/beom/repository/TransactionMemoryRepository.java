package com.beom.repository;

import com.beom.domain.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TransactionMemoryRepository {

    private final static List<Transaction> transactionsStore = new ArrayList<>();


    public Transaction save(Transaction transaction)
    {
        transactionsStore.add(transaction);
        return transaction;
    }

    public Transaction select(Long Id)
    {
        Transaction transaction = transactionsStore.get(Math.toIntExact(Id));
        return transaction;
    }

}
