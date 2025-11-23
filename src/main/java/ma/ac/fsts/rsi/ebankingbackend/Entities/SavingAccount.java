package ma.ac.fsts.rsi.ebankingbackend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import ma.ac.fsts.rsi.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;


@Entity
@DiscriminatorValue("SA")

public class SavingAccount extends BankAccount {
    private double interestRate;
    public SavingAccount() {}

    public SavingAccount(double interestRate) {
        this.interestRate = interestRate;
    }


    public SavingAccount(String id, double balance, Date createdAt, AccountStatus status, Customer customer, List<AccountOperation> accountOperations, double interestRate) {
        super(id, balance, createdAt, status, customer, accountOperations);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
