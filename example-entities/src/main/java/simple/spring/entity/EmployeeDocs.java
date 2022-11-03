package simple.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * create table employee_docs
 * (
 *     doc    json null,
 *     emp_no int as (json_unquote(json_extract(`doc`, _utf8mb4'$.emp_no'))) stored
 *         primary key
 * )
 *     collate = utf8mb4_general_ci;
 * */
//@Entity
//@Table(name = "employee_docs")
//public class EmployeeDocs {
//
//}