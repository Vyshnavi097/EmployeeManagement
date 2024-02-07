package com.example.demo.Controller;

import com.example.demo.Contract.EmployeeRequest;
import com.example.demo.Contract.EmployeeResponse;
import com.example.demo.Model.Employee;
import com.example.demo.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeControllerTest {
    private MockMvc mockMvc;

        @InjectMocks
        private EmployeeController employeeController;

        @Mock
        private EmployeeService employeeService;

        @Autowired
        private WebApplicationContext webApplicationContext;

        @BeforeEach
        public void setUp() {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }


    @Test
    void testBooking() {
        EmployeeRequest Request = new EmployeeRequest("David", "da@gmail.com", "civil");
        EmployeeResponse Response =
                new EmployeeResponse(1L,"David","da@gmail.com","civil");
        when(employeeService.create(Request)).thenReturn(Response);
    }

}
