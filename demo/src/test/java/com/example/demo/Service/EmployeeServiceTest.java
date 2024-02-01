package com.example.demo.Service;

import com.example.demo.Contract.EmployeeRequest;
import com.example.demo.Contract.EmployeeResponse;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {
    @InjectMocks
      private EmployeeService employeeService;

        @Mock
        private EmployeeRepository employeeRepository;

        @Mock
        private ModelMapper modelMapper;

    @BeforeEach
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

    @Test
        public void testCreate() {
        EmployeeRequest employeeRequest=new EmployeeRequest();
        Employee employee=new Employee();
        Employee employee1=new Employee();
        EmployeeResponse employeeResponse=new EmployeeResponse();

        when(modelMapper.map(employeeRequest, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee1);
       when(modelMapper.map(employee1,EmployeeResponse.class)).thenReturn(employeeResponse);

            employeeService.create(employeeRequest);
          verify(modelMapper, times(1)).map(employeeRequest, Employee.class);

            verify(employeeRepository, times(1)).save(employee);
            verify(modelMapper,times(1)).map(employee1,EmployeeResponse.class);
        }
    @Test
    public void testGetById() {

        Long id = 1L;
        Employee employee = new Employee(id, "name", "email", "department");
        EmployeeResponse employeeResponse = new EmployeeResponse("name","email","department");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee, EmployeeResponse.class)).thenReturn(employeeResponse);
        EmployeeResponse result = employeeService.getById(id);

        assertEquals(employeeResponse, result);
        verify(employeeRepository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(employee, EmployeeResponse.class);
    }
}
