package com.netcracker.dao;

import com.netcracker.dto.Employee;
import com.netcracker.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Map<Integer,String> errorMap = new HashMap<>();
    public static void intializeErrorMap(){
        errorMap.put(20001,"Employee should be atleast 18 year old");
        errorMap.put(6512,"Employee should be atleast 18 year old");
        errorMap.put(2291,"Department Not found");
        errorMap.put(1438,"Employee Id must be 6 digits");
        errorMap.put(1,"Employee already exist");
        errorMap.put(2290,"Employee Id should be 6 digits only and should not start with 0");
    }

    public final RowMapper<Employee> employeeRowMapper = (resultSet, rowNumber) -> {
        Employee employee = new Employee();
        employee.setEmployeeId(resultSet.getInt("EMPLOYEE_ID"));
        employee.setFirstName(resultSet.getString("FIRST_NAME"));
        employee.setLastName(resultSet.getString("LAST_NAME"));
        employee.setDateOfJoining(String.valueOf(resultSet.getDate("DATE_OF_JOINING")));
        employee.setDateOfBirth(String.valueOf(resultSet.getDate("DATE_OF_BIRTH")));
        employee.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
        employee.setGrade(resultSet.getString("GRADE"));
        employee.setDesignation(resultSet.getString("DESIGNATION"));
        employee.setGender(resultSet.getString("GENDER"));
        employee.setBasePay(resultSet.getInt("BASE_PAY"));
        return employee;
    };

    public  int getEmployeeCount() {
        return jdbcTemplate.queryForObject(Constant.COUNT_FROM_EMPTABLE,Integer.class);
    }

    public String updateEmployee(Employee employee) {
        Object[] objects = new Object[]{
                employee.getFirstName(),
                employee.getLastName(),
                java.sql.Date.valueOf(employee.getDateOfJoining()),
                java.sql.Date.valueOf(employee.getDateOfBirth()),
                employee.getDepartmentId(),
                employee.getGrade(),
                employee.getDesignation(),
                employee.getGender(),
                employee.getBasePay(),
                employee.getEmployeeId()
        };
        try{
             jdbcTemplate.update(Constant.UPDATE_EMPLOYEE_BY_ID, objects);
             return "true";
        }catch (UncategorizedSQLException ae) {
            EmployeeDao.intializeErrorMap();
            return errorMap.get(ae.getSQLException().getErrorCode());
        }catch (DataIntegrityViolationException ae){
            if(ae.getCause().toString().equals("java.sql.SQLIntegrityConstraintViolationException: ORA-02291: integrity constraint (HR.SYS_C007076) violated - parent key not found\n")){
                return "Department with id "+employee.getDepartmentId()+" not found";
            }else{
                return "Employee Id should be 6 digits only and should not start with 0";
            }
        }catch (Exception ae){
            return "Exception:"+ae.getMessage();
        }
    }

    public String addNewEmployee(Employee employee) {
        Object[] objects = new Object[]{
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                java.sql.Date.valueOf(employee.getDateOfJoining()),
                java.sql.Date.valueOf(employee.getDateOfBirth()),
                employee.getDepartmentId(),
                employee.getGrade(),
                employee.getDesignation(),
                employee.getGender(),
                employee.getBasePay()
        };
        try {
            jdbcTemplate.update(Constant.INSERT_INTO_EMPTABLE_VALUES, objects);
            return "true";
        } catch (UncategorizedSQLException ae) {
            EmployeeDao.intializeErrorMap();
             return errorMap.get(ae.getSQLException().getErrorCode());

        }catch (DataIntegrityViolationException ae){
            if(ae.getCause().toString().equals("java.sql.SQLIntegrityConstraintViolationException: ORA-02291: integrity constraint (HR.SYS_C007076) violated - parent key not found\n")){
                return "Department with id "+employee.getDepartmentId()+" not found";
            }else{
                return "Employee Id should be 6 digits only and should not start with 0";
            }
        }catch (Exception ae){
            return "Exception:"+ae.getMessage();
        }
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query(Constant.SELECT_FROM_EMPTABLE, employeeRowMapper);
    }

    public Employee getEmployeeById(Employee employee) {
        Object[] objects = new Object[]{employee.getEmployeeId()};
        List<Employee> list = jdbcTemplate.query(Constant.SELECT_FROM_EMPTABLE_WHERE_EMPLOYEE_ID, objects, employeeRowMapper);
        if(list.isEmpty()){
            return null;
        }else {
            return list.get(0);
        }
    }


    public List<Employee> getNextEmployees(int offset,int limit) {
        Object[] objects = new Object[]{offset+limit,offset};
        return jdbcTemplate.query(Constant.SHOW_NEXT_OR_PREVIOUS_EMPLOYEES,objects,employeeRowMapper);
    }
}

//(rs, rowNum)->
//        new Employee(
//        rs.getInt("EMPLOYEE_ID"),
//        rs.getString("FIRST_NAME"),
//        rs.getString("LAST_NAME"),
//        rs.getDate("DATE_OF_JOINING").toString(),
//        rs.getDate("DATE_OF_BIRTH").toString(),
//        rs.getInt("DEPARTMENT_ID"),
//        rs.getString("GRADE"),
//        rs.getString("DESIGNATION"),
//        rs.getString("GENDER"),
//        rs.getInt("BASE_PAY")
//        )
