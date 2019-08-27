package com.netcracker.utility;

public class Constant {
    private Constant(){}
    public static final String getAdminByUsernameAndPassword = "SELECT COUNT(*) FROM admin WHERE USERNAME=? AND PASSWORD = ?";
    public static final String addNewEmployee = "INSERT INTO EMPTABLE VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static final String getAllEmployees = "SELECT * FROM emptable";
    public static final String getEmployeeById = "SELECT * FROM emptable WHERE EMPLOYEE_ID = ?";
    public static final String getEmployeeCount = "SELECT COUNT(*) FROM emptable";
    public static final String updateEmployeeById = "UPDATE EMPTABLE" + " SET first_name = ?," +
            "last_name = ?," +
            "date_of_joining = ?," +
            "date_of_birth = ?," +
            "department_id = ?," +
            "grade = ?," +
            "designation = ?," +
            "gender = ?," +
            "base_pay = ? " +
            "WHERE employee_id = ?";
    public static final String showNextOrPreviousEmployees = "SELECT *\n" +
            "FROM   (SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DATE_OF_JOINING, DATE_OF_BIRTH, DEPARTMENT_ID, GRADE, DESIGNATION, GENDER, BASE_PAY, rownum AS rnum\n" +
            "        FROM   (SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DATE_OF_JOINING, DATE_OF_BIRTH, DEPARTMENT_ID, GRADE, DESIGNATION, GENDER, BASE_PAY\n" +
            "                FROM   EMPTABLE\n" +
            "                ORDER BY FIRST_NAME)\n" +
            "        WHERE rownum <= ?)\n" +
            "WHERE  rnum > ?";
}
