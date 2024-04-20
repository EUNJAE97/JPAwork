package com.ohgiraffers.jpawork.employee.controller;

import com.ohgiraffers.jpawork.common.Pagenation;
import com.ohgiraffers.jpawork.common.PagingButton;
import com.ohgiraffers.jpawork.employee.dto.DepartmentDTO;
import com.ohgiraffers.jpawork.employee.dto.EmployeeDTO;
import com.ohgiraffers.jpawork.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
private final EmployeeService employeeService;
    @GetMapping("/{empId}")
    public String findEmpById(@PathVariable Integer empId, Model model) {
        EmployeeDTO emp = employeeService.findEmpById(empId);
        model.addAttribute("emp", emp);
        return "employee/detail";
    }

    @GetMapping("/list")
    public String findEmpList(Model model, @PageableDefault Pageable pageable) {
        Page<EmployeeDTO> empList = employeeService.findEmpList(pageable);
        PagingButton paging = Pagenation.getPagingButtonInfo(empList);

        model.addAttribute("empList", empList);
        model.addAttribute("paging", paging);
        return "employee/list";
    }

    @GetMapping("/querymethod")
    public void querymethodPage() {

    }

    @GetMapping("/search")
    public String findBySalary(@RequestParam int salary,Model model) {
        List<EmployeeDTO> empList = employeeService.findBySalary(salary);
        model.addAttribute("empList", empList);
        return"employee/searchResult";
    }

    @GetMapping("/regist")
    public void registEmp() {

    }

    @GetMapping("/department")
    @ResponseBody
    public List<DepartmentDTO> findDepartmentList() {
        return employeeService.findAllDepartment();
    }

    @PostMapping("/regist")
    public String registNewEmp(@ModelAttribute EmployeeDTO employeeDTO) {
        employeeDTO.setJobCode("J&");
        employeeDTO.setSalLevel("S6");
        employeeService.registEmp(employeeDTO);
        return "redirect:/employee/list";
    }

    @GetMapping("/modify")
    public void modifyPage() {

    }

    @PostMapping("/modify")
    public String modifyEmp(@ModelAttribute EmployeeDTO employeeDTO) {
        employeeService.modifyEmp(employeeDTO);
        return"redirect:/employee/" + employeeDTO.getEmpId();
    }
    @GetMapping("/delete")
    public void deletePage() {
    }

    @PostMapping("/delete")
    public String deleteEmp(@RequestParam Integer empId) {
        employeeService.deleteEmp(empId);
        return "redirect:/employee/list";
    }


}
