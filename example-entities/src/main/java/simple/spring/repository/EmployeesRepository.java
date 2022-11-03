package simple.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.spring.entity.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

}
