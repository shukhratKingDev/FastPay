package com.pay.fastpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String message;
    private String fromCard;
    private String toCard;
    private double amount;
    private boolean success;

    public Response(String message,boolean success) {
        this.message = message;
        this.success=success;
    }
}
