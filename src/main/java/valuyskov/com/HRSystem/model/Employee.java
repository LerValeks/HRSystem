package valuyskov.com.HRSystem.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  @Setter(AccessLevel.PROTECTED) Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email", unique=true)
    private String email;
    @Column(name="age")
    @Min(18)
    private Integer age;
    @Column(name= "salary")
    @Min(0)
    private Integer salary;



    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public Employee(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
}
