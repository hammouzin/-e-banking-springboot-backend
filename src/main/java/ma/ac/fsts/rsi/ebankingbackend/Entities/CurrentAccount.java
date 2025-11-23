package ma.ac.fsts.rsi.ebankingbackend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import ma.ac.fsts.rsi.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("CA")
public class CurrentAccount extends BankAccount{
    private double overDraft;
    public CurrentAccount() {}
    public CurrentAccount(double overDraft) {
        this.overDraft = overDraft;
    }

    public CurrentAccount(String id, double balance, Date createdAt, AccountStatus status, Customer customer, List<AccountOperation> accountOperations, double overDraft) {
        super(id, balance, createdAt, status, customer, accountOperations);
        this.overDraft = overDraft;
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }
}
