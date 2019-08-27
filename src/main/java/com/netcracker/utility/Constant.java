package com.netcracker.utility;

public class Constant {
    private Constant(){}
    public static final String ADMIN_HOMEPAGE =  "adminHomepage";
    public static final String ALL_EMPLOYEES = "allEmployees";
    public static final String EDIT_EMPLOYEES = "editEmployee";
    public static final String EMPLOYEE_REGISTRATION = "employeeRegistration";
    public static final String LOGIN_PAGE="login";
    public static final String SEARCH_EMPLOYEE = "searchEmployee";

    public static final String USERNAME="username";

    public static final String LIMIT ="limit";
    public static final String OFFSET="offset";
    public static final String USERNAME_AND_PASSWORD = "SELECT COUNT(*) FROM admin WHERE USERNAME=? AND PASSWORD = ?";
    public static final String INSERT_INTO_EMPTABLE_VALUES = "INSERT INTO EMPTABLE VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_FROM_EMPTABLE = "SELECT * FROM emptable";
    public static final String SELECT_FROM_EMPTABLE_WHERE_EMPLOYEE_ID = "SELECT * FROM emptable WHERE EMPLOYEE_ID = ?";
    public static final String COUNT_FROM_EMPTABLE = "SELECT COUNT(*) FROM emptable";
    public static final String UPDATE_EMPLOYEE_BY_ID = "UPDATE EMPTABLE" + " SET first_name = ?," +
            "last_name = ?," +
            "date_of_joining = ?," +
            "date_of_birth = ?," +
            "department_id = ?," +
            "grade = ?," +
            "designation = ?," +
            "gender = ?," +
            "base_pay = ? " +
            "WHERE employee_id = ?";
    public static final String SHOW_NEXT_OR_PREVIOUS_EMPLOYEES = "SELECT *\n" +
            "FROM   (SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DATE_OF_JOINING, DATE_OF_BIRTH, DEPARTMENT_ID, GRADE, DESIGNATION, GENDER, BASE_PAY, rownum AS rnum\n" +
            "        FROM   (SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DATE_OF_JOINING, DATE_OF_BIRTH, DEPARTMENT_ID, GRADE, DESIGNATION, GENDER, BASE_PAY\n" +
            "                FROM   EMPTABLE\n" +
            "                ORDER BY FIRST_NAME)\n" +
            "        WHERE rownum <= ?)\n" +
            "WHERE  rnum > ?";
}
