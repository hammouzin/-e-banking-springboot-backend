package ma.ac.fsts.rsi.ebankingbackend.Entities;

import jakarta.persistence.*;
import ma.ac.fsts.rsi.ebankingbackend.enums.OperationType;

import java.util.Date;

@Entity
public class AccountOperation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate ;
    private double amount ;
    @Enumerated(EnumType.STRING)
    private OperationType type ;
    private String description ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    private BankAccount bankAccount ;


    public AccountOperation() {}

    public AccountOperation(Date operationDate, double amount, OperationType type, BankAccount bankAccount, String description) {
        this.operationDate = operationDate;
        this.amount = amount;
        this.type = type;
        this.bankAccount = bankAccount;
        this.description = description;

    }

    public Long getId() { return id; }
    public Date getOperationDate() { return operationDate; }
    public double getAmount() { return amount; }
    public OperationType getType() { return type; }
    public BankAccount getBankAccount() { return bankAccount; }

    public void setId(Long id) { this.id = id; }
    public void setOperationDate(Date operationDate) { this.operationDate = operationDate; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setType(OperationType type) { this.type = type; }
    public void setBankAccount(BankAccount bankAccount) { this.bankAccount = bankAccount; }

}
