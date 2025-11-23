package ma.ac.fsts.rsi.ebankingbackend.dtos;

import jakarta.persistence.*;
import ma.ac.fsts.rsi.ebankingbackend.Entities.AccountOperation;
import ma.ac.fsts.rsi.ebankingbackend.Entities.Customer;
import ma.ac.fsts.rsi.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;


public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt ;
    private AccountStatus status  ;

    private CustomerDTO customerDTO;
    private double interestRate;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }
    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
