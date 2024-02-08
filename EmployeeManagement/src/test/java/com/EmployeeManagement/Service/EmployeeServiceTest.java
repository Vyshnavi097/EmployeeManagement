package com.EmployeeManagement.Service;

import com.EmployeeManagement.Model.Employee;
import com.EmployeeManagement.Repository.EmployeeRepository;
import com.EmployeeManagement.Contract.request.EmployeeRequest;
import com.EmployeeManagement.Contract.response.EmployeeResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    public void testBookingById() {
        Long id = 1L;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeService.getById(id));
    }
    @Test
    void testCreate() {
        EmployeeRequest request = new EmployeeRequest("David", "da@gmail.com", "civil");
        Employee employee = modelMapper.map(request, Employee.class);
        EmployeeResponse expectedResponse = modelMapper.map(employee,EmployeeResponse.class);

        when(employeeRepository.save(any())).thenReturn(employee);

        EmployeeResponse actualResponse = employeeService.create(request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getByDepartTest() {
        String dname = "Sales";
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeResponse> result = employeeService.getByDepart(dname);

        verify(employeeRepository).findAll();
        assertEquals(2, result.size());

        List<Employee> expectedEmployees = employees.stream()
                .filter(employee -> employee.getDepartment().equals(dname))
                .collect(Collectors.toList());

        assertEquals(expectedEmployees.size(), result.size());
    }


}
