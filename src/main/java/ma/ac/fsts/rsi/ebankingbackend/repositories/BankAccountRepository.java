package ma.ac.fsts.rsi.ebankingbackend.repositories;

import ma.ac.fsts.rsi.ebankingbackend.Entities.BankAccount;
import ma.ac.fsts.rsi.ebankingbackend.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    
}
