package ma.ac.fsts.rsi.ebankingbackend.services;

import ma.ac.fsts.rsi.ebankingbackend.Entities.*;
import ma.ac.fsts.rsi.ebankingbackend.dtos.*;
import ma.ac.fsts.rsi.ebankingbackend.enums.OperationType;
import ma.ac.fsts.rsi.ebankingbackend.exceptions.BalanceNotFoundException;
import ma.ac.fsts.rsi.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.ac.fsts.rsi.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ac.fsts.rsi.ebankingbackend.mappers.BankAccountMapperImpl;
import ma.ac.fsts.rsi.ebankingbackend.repositories.AccountOperationRepository;
import ma.ac.fsts.rsi.ebankingbackend.repositories.BankAccountRepository;
import ma.ac.fsts.rsi.ebankingbackend.repositories.CustomerRepository;
import org.springframework.data.domain.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {


    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;

    private BankAccountMapperImpl dtoMapper;

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public BankAccountServiceImpl(CustomerRepository customerRepository,
                                  BankAccountRepository bankAccountRepository,
                                  AccountOperationRepository accountOperationRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.dtoMapper = new BankAccountMapperImpl();
    }


    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("savine a new customer");
        Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
       Customer savedCustomer =  customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance,
                                                        double overDraft,
                                                        Long customerId)
            throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null) ;
        if(customer==null)
            throw new CustomerNotFoundException("Customer not found");
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);
        return dtoMapper.fromCurrentAccount(savedBankAccount);
    }

    @Override
    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance,
                                                      double interestRate,
                                                      Long customerId)
            throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null) ;
        if(customer==null)
            throw new CustomerNotFoundException("Customer not found");
        SavingAccount savingAccount = new SavingAccount();

        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
        return dtoMapper.fromSavingBankAccount(savedBankAccount);
    }

    @Override
    public List<BankAccountDTO> bankAccountList() {
       List<BankAccount> BankAccounts = bankAccountRepository.findAll();
        List<BankAccountDTO> bankAccountDTOS = BankAccounts.stream().map(bankAccount -> {
           if(bankAccount instanceof SavingAccount) {
               SavingAccount savingAccount = (SavingAccount) bankAccount;
               return dtoMapper.fromSavingBankAccount(savingAccount);
           } else {
               CurrentAccount currentAccount = (CurrentAccount) bankAccount;
               return dtoMapper.fromCurrentAccount(currentAccount);
           }
       }).collect(Collectors.toList());
        return bankAccountDTOS;
    }


    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        // programmation fonctionnelle en utilisant streams

        List<CustomerDTO> customerDTOS =  customers.stream().map(customer -> dtoMapper.fromCustomer(customer))
                .collect(Collectors.toList());
        /*
        //interactive
        List<CustomerDTO> dtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = dtoMapper.fromCustomer(customer);
            dtos.add(customerDTO);
        }
        return dtos;

         */
        return customerDTOS;
    }

    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.
                findById(accountId).orElseThrow(()
                        ->new BankAccountNotFoundException("Bank not found")) ;
        if (bankAccount instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return dtoMapper.fromSavingBankAccount(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return dtoMapper.fromCurrentAccount(currentAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotFoundException {
        BankAccount bankAccount = bankAccountRepository.
                findById(accountId).orElseThrow(()
                        ->new BankAccountNotFoundException("Bank not found")) ;

        if(bankAccount.getBalance()<amount)
            throw new BalanceNotFoundException("solde insuffisant");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.
                findById(accountId).orElseThrow(()
                        ->new BankAccountNotFoundException("Bank not found")) ;

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+ amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotFoundException, BankAccountNotFoundException {
        debit(accountIdSource,amount,"Transfert to " +accountIdDestination);
        credit(accountIdDestination,amount,"Transfert from " +accountIdSource);
    }
    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new CustomerNotFoundException("Customer not found "));
        return dtoMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("savine a new customer");
        Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer =  customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public void deleteCusomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<AccountOperationDTO> accountHistory(String accountId) {

       List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccount_Id(accountId);
       return  accountOperations.stream().map(op-> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size)
            throws BankAccountNotFoundException {

        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Account not found"));

        Page<AccountOperation> accountOperationsPage =
                accountOperationRepository.findByBankAccount_Id(accountId, PageRequest.of(page, size));

        AccountHistoryDTO dto = new AccountHistoryDTO();

        List<AccountOperationDTO> operationDTOS = accountOperationsPage.getContent()
                .stream()
                .map(op -> dtoMapper.fromAccountOperation(op))
                .collect(Collectors.toList());

        dto.setAccountId(bankAccount.getId());
        dto.setBalance(bankAccount.getBalance());
        dto.setAccountOperations(operationDTOS);

        // Pagination
        dto.setCurrentPage(page);
        dto.setPageSize(size);
        dto.setTotalPages(accountOperationsPage.getTotalPages());

        return dto;
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers=customerRepository.searchCustomer(keyword);
        List<CustomerDTO> customerDTOS = customers.stream().map(cust -> dtoMapper.fromCustomer(cust)).collect(Collectors.toList());
        return customerDTOS;
    }


}
