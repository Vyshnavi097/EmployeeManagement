package com.EmployeeManagement.Controller;

import com.EmployeeManagement.Contract.request.EmployeeRequest;
import com.EmployeeManagement.Contract.response.EmployeeResponse;
import com.EmployeeManagement.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                new EmployeeResponse(1L, "David", "da@gmail.com", "civil");
        when(employeeService.create(Request)).thenReturn(Response);
    }

    @Test
    void testGetById() throws Exception {
        when(employeeService.getById(anyLong()))
                .thenReturn(new EmployeeResponse(1L, "Name", "name@example.org", "Department"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/{id}", 1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"name\":\"Name\",\"email\":\"name@example.org\",\"department\":\"Department\"}"));
    }
    @Test
    public void testEmployeeByDept() throws Exception {
        String dname = "Sales";

        List<EmployeeResponse> employeeList = new ArrayList<>();
        employeeList.add(new EmployeeResponse(1L, "John Doe", dname, "Manager"));
        employeeList.add(new EmployeeResponse(1L, "Jane Doe", dname, "Sales Representative"));

        Mockito.when(employeeService.getByDepart(dname)).thenReturn(employeeList);

        mockMvc.perform(get("/employees")
                        .param("dname", dname))
                .andExpect(status().isOk());
    }



}
