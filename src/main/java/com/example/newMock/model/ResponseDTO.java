package com.example.newMock.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class ResponseDTO {
    public void ResponseDTO(){

    }
    public ResponseDTO(String rqUID, String clientId, String account, String currency, BigDecimal balance, BigDecimal maxLimit) {
        this.rqUID = rqUID;
        this.clientId = clientId;
        this.account = account;
        this.currency = currency;
        this.balance = balance;
        this.maxLimit = maxLimit;
    }
    private String rqUID;
    private String clientId;
    private String account;


    private String currency;
    private BigDecimal balance;
    private BigDecimal maxLimit;
}
