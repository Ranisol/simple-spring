package simple.mybatis.repository;

import org.apache.ibatis.annotations.*;
import simple.mybatis.entity.Department;

@Mapper
public interface DepartmentRepository {
    @Results(id = "department", value = {
            @Result(property = "deptNo", column = "dept_no"),
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "empCount", column = "emp_count")
    })
    @Select("select * from departments where dept_no=#{deptNo}")
    Department findByDeptNo(@Param("deptNo") String deptNo);

    // insert into department values (#{deptNo}, #{deptName}, #{empCount})
    @Insert("insert into departments values (#{deptNo}, #{deptName}, #{empCount})")
    void save(Department department);

    @Update("update departments set dept_name=#{deptName}, emp_count=#{empCount} where dept_no=#{deptNo}")
    void update(Department department);

    @Delete("delete from departments where dept_no=#{deptNo}")
    void delete(String deptNo);
}
