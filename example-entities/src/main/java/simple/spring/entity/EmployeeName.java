package simple.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_name")
public class EmployeeName {
    @Id
    private int emp_no;
    private String firstName;
    private String lastName;
    
}
