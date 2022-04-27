package com.pay.fastpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outcome {
    private Integer id;
    private String from_card_id;
    private String to_card_id;
    private double amount;
    private Date date;
    private double commission_amount;
}
