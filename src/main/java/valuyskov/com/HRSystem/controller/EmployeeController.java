package valuyskov.com.HRSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import valuyskov.com.HRSystem.exception.EmployeeAlreadyExistException;
import valuyskov.com.HRSystem.exception.EmployeeNotFoundException;
import valuyskov.com.HRSystem.exception.ResourceNotFoundException;
import valuyskov.com.HRSystem.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import valuyskov.com.HRSystem.repository.EmployeeRepository;
import valuyskov.com.HRSystem.service.EmployeeService;
import valuyskov.com.HRSystem.service.EmployeeServiceImp;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List> getAllEmployees()  throws EmployeeNotFoundException
    {
        return new ResponseEntity<List>((List) employeeService.getAllEmployees(), HttpStatus.OK);
    }


    @GetMapping("/employees/{id}")
    public ResponseEntity  getEmployeeById(@PathVariable Long id)  throws EmployeeNotFoundException
    {
        try {
            return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException employeeNotFoundException) {
            return new ResponseEntity(employeeNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistException {
        if(employeeService.exist(employee.getEmail())!=true) {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>( HttpStatus.CONFLICT);
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
