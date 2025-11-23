package ma.ac.fsts.rsi.ebankingbackend.mappers;

import ma.ac.fsts.rsi.ebankingbackend.Entities.AccountOperation;
import ma.ac.fsts.rsi.ebankingbackend.Entities.CurrentAccount;
import ma.ac.fsts.rsi.ebankingbackend.Entities.Customer;
import ma.ac.fsts.rsi.ebankingbackend.Entities.SavingAccount;
import ma.ac.fsts.rsi.ebankingbackend.dtos.AccountOperationDTO;
import ma.ac.fsts.rsi.ebankingbackend.dtos.CurrentBankAccountDTO;
import ma.ac.fsts.rsi.ebankingbackend.dtos.CustomerDTO;
import ma.ac.fsts.rsi.ebankingbackend.dtos.SavingBankAccountDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        //customerDTO.setId(customer.getId());
        //customerDTO.setName(customer.getName());
        //customerDTO.setEmail(customer.getEmail());
        return customerDTO ;
    }


    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer ;
    }


    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }


    public SavingAccount fromSavingAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {

        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }


    public CurrentBankAccountDTO fromCurrentAccount(CurrentAccount currentAccount) {
         CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
         BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
         currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
         currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
         return currentBankAccountDTO;
    }


    public CurrentAccount fromCurrentAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }
}
