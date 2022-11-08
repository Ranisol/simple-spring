package simple.mybatis.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import simple.mybatis.entity.Department;

@SpringBootTest
public class DepartmentRepositoryTest {
    @Autowired DepartmentRepository departmentRepository;

    @Test
    @DisplayName("DepartmentRepositoryTest - findById")
    public void DepartmentRepositoryFindById() {
        departmentRepository.save(new Department("d001", "Marketing", null));
        Department department = departmentRepository.findByDeptNo("d001");
        Assertions.assertNotNull(department);
        Assertions.assertEquals("Marketing", department.getDeptName());
        Assertions.assertNull(department.getEmpCount());
        Assertions.assertEquals("d001", department.getDeptNo());
    }


}
