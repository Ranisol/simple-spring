

create table if not exists departments
(
    dept_no   char(4)     not null
    primary key,
    dept_name varchar(40) not null,
    emp_count int         null
    )
    collate = utf8mb4_general_ci;


create table if not exists dept_emp
(
    emp_no    int     not null,
    dept_no   char(4) not null,
    from_date date    not null,
    to_date   date    not null,
    primary key (dept_no, emp_no)
    )
    collate = utf8mb4_general_ci;



create table if not exists dept_manager
(
    dept_no   char(4) not null,
    emp_no    int     not null,
    from_date date    not null,
    to_date   date    not null,
    primary key (dept_no, emp_no)
    )
    collate = utf8mb4_general_ci;



create table if not exists employee_name
(
    emp_no     int         not null
    primary key,
    first_name varchar(14) not null,
    last_name  varchar(16) not null
    )
    collate = utf8mb4_general_ci;

create table if not exists employees
(
    emp_no     int             not null
    primary key,
    birth_date date            not null,
    first_name varchar(14)     not null,
    last_name  varchar(16)     not null,
    gender     enum ('M', 'F') not null,
    hire_date  date            not null
    )
    collate = utf8mb4_general_ci;

create table if not exists employees_comp4k
(
    emp_no     int             not null
    primary key,
    birth_date date            not null,
    first_name varchar(14)     not null,
    last_name  varchar(16)     not null,
    gender     enum ('M', 'F') not null,
    hire_date  date            not null
    )
    collate = utf8mb4_general_ci;


create table if not exists employees_comp8k
(
    emp_no     int             not null
    primary key,
    birth_date date            not null,
    first_name varchar(14)     not null,
    last_name  varchar(16)     not null,
    gender     enum ('M', 'F') not null,
    hire_date  date            not null
    )
    collate = utf8mb4_general_ci;

create table if not exists salaries
(
    emp_no    int  not null,
    salary    int  not null,
    from_date date not null,
    to_date   date not null,
    primary key (emp_no, from_date)
    )
    collate = utf8mb4_general_ci;

create index ix_salary
    on salaries (salary);

create table if not exists tb_dual
(
    fd1 int not null
)
    collate = utf8mb4_general_ci;

create table if not exists titles
(
    emp_no    int         not null,
    title     varchar(50) not null,
    from_date date        not null,
    to_date   date        null,
    primary key (emp_no, from_date, title)
    )
    collate = utf8mb4_general_ci;


