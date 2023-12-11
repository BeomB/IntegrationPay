package com.beom.domain;

import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long transactionID;

    @Column
    int amt;

    @Column
    int goodsAmt;

    @Column
    String goodsName;


}
