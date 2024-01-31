package com.example.demo.Service;

import com.example.demo.Contract.EmployeeRequest;
import com.example.demo.Contract.EmployeeResponse;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Employee> employee=employeeRepository.findById(id);
        return modelMapper.map(employee, EmployeeResponse.class);
    }
    public List<EmployeeResponse> getByDepart(String dname){
        List<Employee> employees=employeeRepository.findAll();
        List<Employee> employees1= employees.stream()
                .filter(employee -> employee.getDepartment().equals(dname))
                .collect(Collectors.toList());
        return employees1.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .collect(Collectors.toList());

    }
}