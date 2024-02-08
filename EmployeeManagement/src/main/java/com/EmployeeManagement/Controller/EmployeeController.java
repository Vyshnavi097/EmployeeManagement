package com.EmployeeManagement.Controller;
import com.EmployeeManagement.Contract.request.EmployeeRequest;
import com.EmployeeManagement.Contract.response.EmployeeResponse;
import com.EmployeeManagement.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor

public class EmployeeController {
    private  final EmployeeService employeeService;

    @PostMapping("/employees")
    public EmployeeResponse create(@Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.create(employeeRequest);
    }
    @GetMapping("/employees/{id}")
    public EmployeeResponse getById(@PathVariable long id){
        return employeeService.getById(id);
    }

    @GetMapping("/employees")
        public List<EmployeeResponse>  departmentById(@RequestParam String departmentName){
        return employeeService.departmentById(departmentName);
    }
    }

