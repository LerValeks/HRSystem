package valuyskov.com.HRSystem.service;

import org.springframework.stereotype.Service;
import valuyskov.com.HRSystem.exception.EmployeeAlreadyExistException;
import valuyskov.com.HRSystem.exception.EntityNotFoundException;
import valuyskov.com.HRSystem.model.Employee;
import valuyskov.com.HRSystem.repository.EmployeeRepository;

import java.util.List;


@Service
public class EmployeeServiceImp implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Override
    public Employee createEmployee(Employee employee) throws EmployeeAlreadyExistException {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public List getAllEmployees() throws EntityNotFoundException {
        return (List) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id)  {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null) {
            throw new EntityNotFoundException(Employee.class, "id", id.toString());
        }
        return employee;
    }

    @Override
    public Long deleteEmployee(Long id) throws EntityNotFoundException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null) {
            throw new EntityNotFoundException(Employee.class, "id", id.toString());
        }
        employeeRepository.deleteById(id);
        return id;
    }

    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeAlreadyExistException {
        return null;
    }
}
