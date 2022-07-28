package valuyskov.com.HRSystem.service;

import valuyskov.com.HRSystem.exception.EmployeeAlreadyExistException;
import valuyskov.com.HRSystem.exception.EntityNotFoundException;
import valuyskov.com.HRSystem.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistException;
    List getAllEmployees() throws EntityNotFoundException;
    Employee getEmployeeById (Long id) throws EntityNotFoundException;

}
