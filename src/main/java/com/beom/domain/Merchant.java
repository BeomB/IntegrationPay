package com.beom.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Merchant {

    @Id
    @Column(name = "MERCHANT_ID")
    private String merchantId;

    @Column(name="MERCHANT_KEY")
    private String merchantKey;


}
