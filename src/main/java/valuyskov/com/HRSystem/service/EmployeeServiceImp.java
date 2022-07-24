package valuyskov.com.HRSystem.service;

import org.springframework.stereotype.Service;
import valuyskov.com.HRSystem.exception.EmployeeAlreadyExistException;
import valuyskov.com.HRSystem.exception.EmployeeNotFoundException;
import valuyskov.com.HRSystem.model.Employee;
import valuyskov.com.HRSystem.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Override
    public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistException {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public List getAllEmployees() throws EmployeeNotFoundException {
        return (List) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        Employee employee;
        if(employeeRepository.findById(id).isEmpty()) {
            throw new EmployeeNotFoundException(Employee.class, "id", id.toString());
        } else  {
            employee = employeeRepository.findById(id).get();
        }
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public boolean exist(String email){
        return employeeRepository.existsByEmail(email);
    }
}
