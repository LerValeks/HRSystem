package valuyskov.com.HRSystem.service;

import valuyskov.com.HRSystem.exception.EntityNotFoundException;
import valuyskov.com.HRSystem.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee updateEmployee ( Employee employee) ;
    Employee updateEmployee ( Employee employee, boolean findByEmail) ;
    Employee createEmployee(Employee employee) ;
    List getAllEmployees() throws EntityNotFoundException;
    Employee getEmployeeById (Long id) throws EntityNotFoundException;
    Long deleteEmployee(Long id) throws EntityNotFoundException;
    Employee getEmployeeByEmail(String email) throws  EntityNotFoundException;

}
