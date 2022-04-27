package com.pay.fastpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private User user;
    private Card fromCard;
    private Card toCard;
    private double amount;
    private double commissionAmount;
}
