package valuyskov.com.HRSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import valuyskov.com.HRSystem.model.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
