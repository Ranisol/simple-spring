package simple.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "dept_manager")
@IdClass(DeptManagerId.class)
public class DeptManager {
    @Id
    private String deptNo;
    @Id
    private int empNo;
    private LocalDate fromDate;
    private LocalDate toDate;
}
