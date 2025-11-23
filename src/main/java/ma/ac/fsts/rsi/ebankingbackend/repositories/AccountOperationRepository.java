package ma.ac.fsts.rsi.ebankingbackend.repositories;

import ma.ac.fsts.rsi.ebankingbackend.Entities.AccountOperation;
import ma.ac.fsts.rsi.ebankingbackend.Entities.Customer;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
     List<AccountOperation> findByBankAccount_Id(String accountId);

     Page<AccountOperation> findByBankAccount_Id(String accountIdr, Pageable pageable);
}
