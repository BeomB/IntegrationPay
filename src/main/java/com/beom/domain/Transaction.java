package com.beom.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    @Column(name = "PRODUCT_COUNT")
    int productCount;

    @Column(name = "CURRENCY")
    String currency;

    @Column(name = "BACK_NOTI_URL")
    String backNotiUrl;

    @Column(name = "FRONT_URL")
    String frontUrl;

    @Column(name ="START_TIME")
    LocalDateTime startTime;

    @Column(name ="END_TIME")
    LocalDateTime endTime;



}
