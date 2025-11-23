package ma.ac.fsts.rsi.ebankingbackend.dtos;

import java.util.List;

public class AccountHistoryDTO {
    private String accountId;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<AccountOperationDTO> accountOperations;


    public AccountHistoryDTO() {
    }

    public AccountHistoryDTO(String accountId, double balance, int currentPage, int totalPages, int pageSize, List<AccountOperationDTO> accountOperations) {
        this.accountId = accountId;
        this.balance = balance;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.accountOperations = accountOperations;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<AccountOperationDTO> getAccountOperations() {
        return accountOperations;
    }

    public void setAccountOperations(List<AccountOperationDTO> accountOperations) {
        this.accountOperations = accountOperations;
    }
}
