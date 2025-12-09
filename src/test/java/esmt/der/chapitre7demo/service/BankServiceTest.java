package esmt.der.chapitre7demo.service;

import esmt.der.chapitre7demo.model.Account;
import esmt.der.chapitre7demo.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private BankService bankService;

    @Test
    void testTransfer_FondsInsuffisants() {
        // GIVEN (Mise en place du scénario)
        Account pauvre = new Account("Etudiant", new BigDecimal("1000")); // Il a 1000
        Account riche = new Account("Banquier", new BigDecimal("50000"));

        // On dit au Mock : "Si on te demande l'ID 1, renvoie le compte 'pauvre'"
        when(accountRepository.findById(1L)).thenReturn(Optional.of(pauvre));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(riche));

        System.out.println("TEST UNITAIRE : Tentative de virer 5000 alors qu'on a 1000...");

        // WHEN & THEN (On vérifie que ça EXPLOSE comme prévu)
        assertThatThrownBy(() -> bankService.transfer(1L, 2L, new BigDecimal("5000")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Fonds insuffisants");

        // Verification : On s'assure que le repository n'a JAMAIS sauvegardé de changement
        verify(accountRepository, never()).save(any());
        System.out.println("TEST UNITAIRE : Succès ! L'exception a bien été levée et rien n'a été sauvegardé.");
    }
}