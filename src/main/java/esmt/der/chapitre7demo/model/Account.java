package esmt.der.chapitre7demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private BigDecimal balance; // On utilise BigDecimal pour l'argent !

    public Account() {}

    public Account(String owner, BigDecimal balance) {
        this.owner = owner;
        this.balance = balance;
    }

    // Getters et Setters standards
    public Long getId() { return id; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public String getOwner() { return owner; }
}