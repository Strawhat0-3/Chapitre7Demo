package esmt.der.chapitre7demo.repository;

import esmt.der.chapitre7demo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // Configure une base H2 en mémoire automatiquement
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveAndFind() {
        // Création
        Employee emp = new Employee("Moustapha", "Der");
        employeeRepository.save(emp);

        // Vérification
        assertThat(employeeRepository.findAll()).hasSize(1);
    }
}