package com.pay.fastpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Integer id;
    private String username;
    private String number;
    private double balance;
    private Date expireDate;
    private boolean active;
}
