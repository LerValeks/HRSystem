package valuyskov.com.HRSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import valuyskov.com.HRSystem.model.Employee;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        boolean existsByEmail(String email);
        Optional<Employee> findByEmail(String email);

}
