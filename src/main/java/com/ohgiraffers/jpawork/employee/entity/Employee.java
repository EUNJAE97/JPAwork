package com.ohgiraffers.jpawork.employee.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    private String empName;
    private String empNo;
    private String email;
    private String phone;
    private int salary;
    private String salLevel;
    private String jobCode;

    @ManyToOne
    @JoinColumn(name = "deptCode")
    private Department department;

    public void modifyEmpName(String empName) {
        this.empName = empName;
    }
}
