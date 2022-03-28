package com.bootcamp.bankdeposit.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("deposit")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Deposit {
    //Ahora soporta transferencias entre mismo cliente o terceros
    @Id
    private String id;
    private Double amount;
    private String currency;
    private String idClient;
    private String fromAccountId;
    private String fromAccountNumber;
    private String toAccountId;
    private String toAccountNumber;
    private String idDepositor;
    private String timestamp;

    public Deposit(Double amount, String currency, String idClient, String fromAccountId, String fromAccountNumber, String toAccountNumber, String toAccountId, String idDepositor, String timestamp) {
        this.amount = amount;
        this.currency = currency;
        this.idClient = idClient;
        this.fromAccountId=fromAccountId;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.idDepositor = idDepositor;
        this.timestamp = timestamp;
    }
}
