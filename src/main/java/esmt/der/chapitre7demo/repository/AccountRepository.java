package esmt.der.chapitre7demo.repository;

import esmt.der.chapitre7demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}