package com.ohgiraffers.jpawork.employee.repository;

import com.ohgiraffers.jpawork.employee.dto.EmployeeDTO;
import com.ohgiraffers.jpawork.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "SELECT e.*, d.dept_title FROM employee e JOIN department d ON e.dept_code = d.dept_id WHERE e.emp_id = :empId"
            , nativeQuery = true)
    Employee findByEmpId(@Param(value = "empId") long empId);


    List<Employee> findBySalaryGreaterThan(int salary);
}
