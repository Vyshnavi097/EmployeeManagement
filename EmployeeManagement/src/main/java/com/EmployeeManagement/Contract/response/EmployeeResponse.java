package com.EmployeeManagement.Contract.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private long id;
    private String name;
    private String email;
    private String department;
}
