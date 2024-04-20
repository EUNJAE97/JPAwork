package com.ohgiraffers.jpawork.employee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDTO {
    private int empId;
    private String empName;
    private String empNo;
    private String email;
    private String phone;
    private String salLevel;
    private String jobCode;
    private DepartmentDTO department;
    private int salary;
}
