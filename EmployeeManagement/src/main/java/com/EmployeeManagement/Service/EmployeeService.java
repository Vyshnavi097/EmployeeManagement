package com.EmployeeManagement.Service;

import com.EmployeeManagement.Contract.request.EmployeeRequest;
import com.EmployeeManagement.Contract.response.EmployeeResponse;
import com.EmployeeManagement.Model.Employee;
import com.EmployeeManagement.Repository.EmployeeRepository;
import com.EmployeeManagement.exception.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    public EmployeeResponse create(EmployeeRequest employeeRequest){
        Employee employee=modelMapper.map(employeeRequest, Employee.class);
        Employee employee1=employeeRepository.save(employee);
        return modelMapper.map(employee1, EmployeeResponse.class);
    }

    public EmployeeResponse getById(long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("not found"));
        return modelMapper.map(employee, EmployeeResponse.class);
    }
    public List<EmployeeResponse> departmentById(String departmentName){
        List<Employee> employees=employeeRepository.findAll();
        List<Employee> employees1= employees.stream()
                .filter(employee -> employee.getDepartment().equals(departmentName))
                .collect(Collectors.toList());
        return employees1.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .collect(Collectors.toList());

    }
}