package esmt.der.chapitre7demo.service;

import esmt.der.chapitre7demo.model.Account;
import esmt.der.chapitre7demo.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankService {

    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional // Important : tout réussit ou tout échoue
    public void transfer(Long idSource, Long idTarget, BigDecimal amount) {
        System.out.println("\n--- [LOG BUSINESS] Début du transfert de " + amount + " CFA ---");

        // 1. Récupération des comptes
        Account source = accountRepository.findById(idSource)
                .orElseThrow(() -> new RuntimeException("Compte source introuvable"));
        Account target = accountRepository.findById(idTarget)
                .orElseThrow(() -> new RuntimeException("Compte cible introuvable"));

        System.out.println("--- [LOG BUSINESS] Solde actuel de " + source.getOwner() + ": " + source.getBalance());

        // 2. Vérification du solde (Règle Métier)
        if (source.getBalance().compareTo(amount) < 0) {
            System.err.println("--- [LOG BUSINESS] ECHEC : Fonds insuffisants ! ---");
            throw new RuntimeException("Fonds insuffisants");
        }

        //on commente pour prouver les risques

        // if (source.getBalance().compareTo(amount) < 0) {
//     System.err.println("--- [LOG BUSINESS] ECHEC : Fonds insuffisants ! ---");
//     throw new RuntimeException("Fonds insuffisants");
// }

        // 3. Opération
        source.setBalance(source.getBalance().subtract(amount));
        target.setBalance(target.getBalance().add(amount));

        // 4. Sauvegarde
        accountRepository.save(source);
        accountRepository.save(target);

        System.out.println("--- [LOG BUSINESS] SUCCES : Nouveau solde de " + source.getOwner() + ": " + source.getBalance());
        System.out.println("--- [LOG BUSINESS] Fin du transfert ---\n");
    }
}