package ma.ac.fsts.rsi.ebankingbackend.dtos;

import jakarta.persistence.*;
import ma.ac.fsts.rsi.ebankingbackend.Entities.BankAccount;
import ma.ac.fsts.rsi.ebankingbackend.enums.OperationType;

import java.util.Date;


public class AccountOperationDTO {


    private Long id;
    private Date operationDate ;
    private double amount ;
    private OperationType type ;
    private String description ;








    public AccountOperationDTO() {}

    public AccountOperationDTO(Date operationDate, double amount, OperationType type, String description) {
        this.operationDate = operationDate;
        this.amount = amount;
        this.type = type;
        this.description = description;

    }

    public Long getId() { return id; }
    public Date getOperationDate() { return operationDate; }
    public double getAmount() { return amount; }
    public OperationType getType() { return type; }
    public String getDescription() {
        return description;
    }


    public void setId(Long id) { this.id = id; }
    public void setOperationDate(Date operationDate) { this.operationDate = operationDate; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setType(OperationType type) { this.type = type; }
    public void setDescription(String description) {
        this.description = description;
    }


}
