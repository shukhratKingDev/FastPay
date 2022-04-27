package com.pay.fastpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    private Integer id;
    private String from_card_Id;
    private String to_card_Id;
    private double amount;
    private Date date;
}
