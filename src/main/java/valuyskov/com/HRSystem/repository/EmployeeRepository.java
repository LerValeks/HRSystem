package valuyskov.com.HRSystem.repository;

import valuyskov.com.HRSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    public Optional<Employee> findByEmail (String email);
    boolean existsByEmail(String email);
}
