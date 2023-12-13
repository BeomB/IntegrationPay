package com.beom.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long transactionID;

    @Column(name = "AMOUNT")
    int amount;

    @Column(name = "PRODUCT_NAME")
    String productName;


}
