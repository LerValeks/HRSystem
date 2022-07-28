package valuyskov.com.HRSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import valuyskov.com.HRSystem.exception.EmployeeAlreadyExistException;
import valuyskov.com.HRSystem.exception.EntityNotFoundException;
import valuyskov.com.HRSystem.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import valuyskov.com.HRSystem.service.EmployeeService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List> getAllEmployees()  throws EntityNotFoundException
    {
        return new ResponseEntity<List>((List) employeeService.getAllEmployees(), HttpStatus.OK);
    }


    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id)  throws EntityNotFoundException
    {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistException {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }


//    @PutMapping("/employees/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
//        Employee employee = employeeRepository.findById(id).orElseThrow(()-> ;
//        employee.setFirstName(employeeDetails.getFirstName());
//        employee.setLastName(employeeDetails.getLastName());
//        employee.setEmail(employeeDetails.getEmail());
//        Employee updatedEmployee = employeeRepository.save(employee);
//        return ResponseEntity.ok(updatedEmployee);
//    }
//
//    @DeleteMapping("/employees/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
//        Employee employee = employeeRepository.findById(id).orElseThrow(()-> ;
//        employeeRepository.delete(employee);
//        Map<String, Boolean> response  = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }

}
