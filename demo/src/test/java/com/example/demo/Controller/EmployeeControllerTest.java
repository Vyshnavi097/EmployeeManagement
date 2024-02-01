package com.example.demo.Controller;

import com.example.demo.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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


}
