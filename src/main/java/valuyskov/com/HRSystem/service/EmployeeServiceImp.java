package valuyskov.com.HRSystem.service;

import org.springframework.stereotype.Service;
import valuyskov.com.HRSystem.exception.EntityNotFoundException;
import valuyskov.com.HRSystem.model.Employee;
import valuyskov.com.HRSystem.repository.EmployeeRepository;

import javax.persistence.EntityExistsException;
import java.util.List;


@Service

public class EmployeeServiceImp implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Override
    public Employee createEmployee(Employee employee) {
        boolean employeeAlreadyExist = employeeRepository.existsByEmail(employee.getEmail());
        if (!employeeAlreadyExist) {
            Employee savedEmployee = employeeRepository.save(employee);
            return savedEmployee;
        } else {
            throw new EntityExistsException("Employee with such email already exist: " + employee.getEmail());
        }
    }

    @Override
    public List getAllEmployees() throws EntityNotFoundException {
        return (List) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new EntityNotFoundException(Employee.class, "id", id.toString());
        }

        return employee;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        if (employee == null) {
            throw new EntityNotFoundException(Employee.class, "email", email);
        }
        return employee;
    }



    @Override
    public Long deleteEmployee(Long id) throws EntityNotFoundException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new EntityNotFoundException(Employee.class, "id", id.toString());
        }
        employeeRepository.deleteById(id);
        return id;
    }

    @Override
    public Employee updateEmployee( Employee employee) {
        if (!employeeRepository.existsByEmail(employee.getEmail())) {
            employeeRepository.save(employee);
            return employee;
        }
        else {
            throw new EntityExistsException("Employee with such email already exist: " + employee.getEmail());
        }

    }

    public Employee updateEmployee( Employee employee, boolean findByEmail) {
            employeeRepository.save(employee);
            return employee;

    }




}
