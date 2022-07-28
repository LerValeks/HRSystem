package valuyskov.com.HRSystem.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import valuyskov.com.HRSystem.HrSystemApplication;
import valuyskov.com.HRSystem.model.Employee;
import valuyskov.com.HRSystem.service.EmployeeService;

import javax.faces.application.Application;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class EmployeeControllerTest {

    @InjectMocks
    public EmployeeController employeeController;

    @Mock
    public EmployeeService employeeService;


    @Test
    public void testAddEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employeeTestAdd = new Employee("AL", "Val", "al1@gmail.com");


        when(employeeService.createEmployee(employeeTestAdd)).thenReturn(employeeTestAdd);
    }

    @Test
    public void testFindAll() {
        // given
        Employee employee1 = new Employee("AL", "Val", "al1@gmail.com");
        Employee employee2 = new Employee("AL2", "Val2", "al2@gmail.com");
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);


        when(employeeService.getAllEmployees()).thenReturn(employees);

        // when
        ResponseEntity<List> result = employeeController.getAllEmployees();

        // then
        assertThat(result.getBody().size()).isEqualTo(2);

        Employee employeeTest1 = (Employee) result.getBody().get(0);
        assertThat(employeeTest1.getFirstName())
                .isEqualTo(employee1.getFirstName());

        Employee employeeTest2 = (Employee) result.getBody().get(1);
        assertThat(employeeTest2.getFirstName())
                .isEqualTo(employee2.getFirstName());
    }
}
