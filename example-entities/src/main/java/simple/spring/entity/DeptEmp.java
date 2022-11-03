package simple.spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dept_emp")
@IdClass(DeptEmpId.class)
@Getter
@Setter
public class DeptEmp {
    @Id
    @Column(name = "dept_no", length = 4)
    private String deptNo;
    @Id
    @Column(name = "emp_no")
    private int empNo;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
}
