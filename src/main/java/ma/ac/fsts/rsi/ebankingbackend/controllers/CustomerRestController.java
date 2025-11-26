package ma.ac.fsts.rsi.ebankingbackend.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.ac.fsts.rsi.ebankingbackend.Entities.Customer;
import ma.ac.fsts.rsi.ebankingbackend.dtos.CustomerDTO;
import ma.ac.fsts.rsi.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ac.fsts.rsi.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers() {
        return
                bankAccountService.listCustomers();
    }

    public CustomerRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCusomer(id);
    }

    @GetMapping("customers/search")
    public List<CustomerDTO> searchCustomer(@RequestParam (name = "keyword", defaultValue = "") String keyword) {
        return bankAccountService.searchCustomers("%" + keyword + "%");
    }
}
