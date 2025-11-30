package esmt.der.chapitre7demo.repository;

import esmt.der.chapitre7demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
