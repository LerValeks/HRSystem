package valuyskov.com.HRSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import valuyskov.com.HRSystem.HrSystemApplication;
import valuyskov.com.HRSystem.model.Employee;
import valuyskov.com.HRSystem.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    private static final String INVALID_AGE_FORMAT_INPUT = "C:\\Users\\User\\Desktop\\Programming\\Java Programming\\HRSystem\\src\\test\\resources\\Employee\\input_invalid_age_format";

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


    @Test
    public void whenIncorectFormatOfAgePosted_ThenGenerateDeserializeError() throws Exception {


//        String json = new String ("{object=Employee, field=age, rejectedValue=null, message=Cannot deserialize value of type `java.lang.Integer` from String \"text\": not a valid `java.lang.Integer` value}");

        this.mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +"   \"firstName\": \"AL\",\n" + "   \"lastName\": \"Val\",\n" + "   \"email\": \"al1@gmail.com\",\n" + "   \"age\": \"text\",\n" + "   \"salary\":100\n" +
                        " }"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Cannot deserialize value of type `java.lang.Integer` from String \\\"text\\\": not a valid `java.lang.Integer` value")));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.subErrors").value(json));
    }

    @Test
    public void whenIncorectValueOfAgePosted_ThenGenerateValidationError() throws Exception {


//        String json = new String ("{object=Employee, field=age, rejectedValue=null, message=Cannot deserialize value of type `java.lang.Integer` from String \"text\": not a valid `java.lang.Integer` value}");

        this.mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +"   \"firstName\": \"AL\",\n" + "   \"lastName\": \"Val\",\n" + "   \"email\": \"al1@gmail.com\",\n" + "   \"age\":17,\n" + "   \"salary\":100\n" +
                        " }"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("\"message\":\"Validation error\"")));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.subErrors").value(json));
    }

}
