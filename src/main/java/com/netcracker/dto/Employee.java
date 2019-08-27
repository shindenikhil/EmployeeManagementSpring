package com.netcracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String dateOfJoining;
    private String dateOfBirth;
    private Integer departmentId;
    private String grade;
    private String designation;
    private String gender;
    private Integer basePay;
    private Integer count;
}
