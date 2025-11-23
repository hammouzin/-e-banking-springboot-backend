package ma.ac.fsts.rsi.ebankingbackend.services;

import ma.ac.fsts.rsi.ebankingbackend.Entities.BankAccount;
import ma.ac.fsts.rsi.ebankingbackend.Entities.CurrentAccount;
import ma.ac.fsts.rsi.ebankingbackend.Entities.Customer;
import ma.ac.fsts.rsi.ebankingbackend.Entities.SavingAccount;
import ma.ac.fsts.rsi.ebankingbackend.dtos.*;
import ma.ac.fsts.rsi.ebankingbackend.exceptions.BalanceNotFoundException;
import ma.ac.fsts.rsi.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.ac.fsts.rsi.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO );
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;


    List<BankAccountDTO> bankAccountList();
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotFoundException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotFoundException, BankAccountNotFoundException;

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCusomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);


    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
