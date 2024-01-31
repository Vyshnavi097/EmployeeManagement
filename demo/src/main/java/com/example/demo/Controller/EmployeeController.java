package com.example.demo.Controller;

import com.example.demo.Contract.EmployeeRequest;
import com.example.demo.Contract.EmployeeResponse;
import com.example.demo.Service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public EmployeeResponse create(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.create(employeeRequest);
    }
    @GetMapping("/employees/{id}")
    public EmployeeResponse getById(@PathVariable long id){
        return employeeService.getById(id);
    }

    @GetMapping("/employees")
        public List<EmployeeResponse>  employeeByDept(@RequestParam String dname){
        return employeeService.getByDepart(dname);
    }
    }

