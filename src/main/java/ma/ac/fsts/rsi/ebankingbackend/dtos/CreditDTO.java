package ma.ac.fsts.rsi.ebankingbackend.dtos;

public class CreditDTO {


    private String accountId;
    private double amount;
    private String description;


    public CreditDTO() {
    }

    public CreditDTO(String accountId, double amount, String description) {
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
