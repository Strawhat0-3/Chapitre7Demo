package esmt.der.chapitre7demo.service;

import esmt.der.chapitre7demo.model.Account;
import esmt.der.chapitre7demo.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // Charge tout le contexte Spring + Base de données H2
class BankIntegrationTest {

    @Autowired
    private BankService bankService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testTransfertComplet() {
        // 1. Préparation de la base de données (Vraie insertion en H2)
        Account source = accountRepository.save(new Account("Alice", new BigDecimal("10000")));
        Account cible = accountRepository.save(new Account("Bob", new BigDecimal("2000")));

        System.out.println("TEST INTEGRATION : Alice (10000) envoie 3000 à Bob (2000)");

        // 2. Exécution du service (Vrai appel)
        bankService.transfer(source.getId(), cible.getId(), new BigDecimal("3000"));

        // 3. Vérification en base de données (La vérité)
        Account sourceApres = accountRepository.findById(source.getId()).get();
        Account cibleApres = accountRepository.findById(cible.getId()).get();

        // Alice doit avoir 7000 (10000 - 3000)
        assertThat(sourceApres.getBalance()).isEqualByComparingTo(new BigDecimal("7000"));
        // Bob doit avoir 5000 (2000 + 3000)
        assertThat(cibleApres.getBalance()).isEqualByComparingTo(new BigDecimal("5000"));

        System.out.println("TEST INTEGRATION : Vérification DB OK. Alice a bien 7000.");
    }
}