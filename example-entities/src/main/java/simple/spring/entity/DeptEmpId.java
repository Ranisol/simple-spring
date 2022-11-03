package simple.spring.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DeptEmpId{

    @Column(name = "dept_no", length = 4)
    private String deptNo;
    @Column(name = "emp_no")
    private int empNo;
}
