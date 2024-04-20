package com.ohgiraffers.jpawork.employee.service;

import com.ohgiraffers.jpawork.employee.dto.DepartmentDTO;
import com.ohgiraffers.jpawork.employee.dto.EmployeeDTO;
import com.ohgiraffers.jpawork.employee.entity.Department;
import com.ohgiraffers.jpawork.employee.entity.Employee;
import com.ohgiraffers.jpawork.employee.repository.DepartmentRepository;
import com.ohgiraffers.jpawork.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    // 특정 직원 조회
    public EmployeeDTO findEmpById(Integer empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(employee,EmployeeDTO.class);
    }

    public Page<EmployeeDTO> findEmpList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0?0 : pageable.getPageNumber()-1,
                pageable.getPageSize(),
                Sort.by("empId").descending()
        );
        Page<Employee> empList = employeeRepository.findAll(pageable);

        return empList.map(emp -> modelMapper.map(emp,EmployeeDTO.class));
    }

    public List<EmployeeDTO> findBySalary(int salary) {
        List<Employee> empList = employeeRepository.findBySalaryGreaterThan(salary);
        return empList.stream()
                .map(emp -> modelMapper.map(emp, EmployeeDTO.class))
                .toList();
    }

    public List<DepartmentDTO> findAllDepartment() {
        List<Department> departmentList = departmentRepository.findAllDepartment();
        return departmentList.stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class))
                .toList();
    }
@Transactional
    public void registEmp(EmployeeDTO employeeDTO) {
    employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
    }
@Transactional
    public void modifyEmp(EmployeeDTO employeeDTO) {
        Employee foundEmp = employeeRepository.findById(employeeDTO.getEmpId()).orElseThrow();
        foundEmp.modifyEmpName(employeeDTO.getEmpName());
    }
@Transactional
    public void deleteEmp(Integer empId) {
        employeeRepository.deleteById(empId);
    }
}
