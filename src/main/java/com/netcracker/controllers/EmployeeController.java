package com.netcracker.controllers;


import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeServices;
import com.netcracker.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/adminHomepage.html")

    public String getAdminHomepage(Model model,HttpSession session){
        if(session.getAttribute(Constant.USERNAME)!=null) {
            return "adminHomepage";
        }else{
            return "redirect:index.html";
        }
    }

    @PostMapping("/searchEmployee.html")
    public String getEmployeeSearchForm(Model model,HttpSession session){
        if(session.getAttribute(Constant.USERNAME)!=null){
            model.addAttribute("employee", new Employee());
            return Constant.SEARCH_EMPLOYEE;
        }else{
            return "redirect:index.html";
        }
    }

    @PostMapping(value = "submitEmployeeSearchForm.html")
    public String submitEmployeeSearchForm(@ModelAttribute("employee")Employee employee, Model model, HttpSession session){
        if(session.getAttribute(Constant.USERNAME)!=null){

            List<String> listOfGrades = new ArrayList<>();
            listOfGrades.add("G1");
            listOfGrades.add("G2");
            listOfGrades.add("G3");
            listOfGrades.add("G4");
            listOfGrades.add("G5");
            listOfGrades.add("G6");
            listOfGrades.add("G7");
            model.addAttribute("grade", listOfGrades);
            List<String> gender = new ArrayList<>();
            gender.add("Male");
            gender.add("Female");
            model.addAttribute("gender", gender);

            Employee retrievedEmployee = employeeServices.getEmployeeById(employee.getEmployeeId());
            if(retrievedEmployee!=null) {
                model.addAttribute("retrievedEmployee",retrievedEmployee );
                return Constant.EDIT_EMPLOYEES;
            }else{
                model.addAttribute("errorMessage","Employee not found");
                model.addAttribute("employee",new Employee());
                return Constant.SEARCH_EMPLOYEE;

            }
        }else{
            return "redirect:index.html";
        }
    }

    @PostMapping(value = "updateEmployee.html")
    public String updateEmployee(@ModelAttribute("retrievedEmployee")Employee employee,Model model,HttpSession session){
        if(session.getAttribute(Constant.USERNAME)!=null){
        String message = employeeServices.updateEmployee(employee);
            if(message.equals("true")){
                model.addAttribute("successMessage","Employee updated successfully");
                model.addAttribute("employee",new Employee());
                return Constant.SEARCH_EMPLOYEE;
            }else{
                model.addAttribute("errorMessage",message);
                model.addAttribute("employee",new Employee());
                return Constant.SEARCH_EMPLOYEE;
            }
        }else{
            return "redirect:index.html";
        }
    }

    @PostMapping(value = "getEmployeeRegistrationForm.html")
    public String getEmployeeRegistrationForm(Model model,HttpSession session) {
        if(session.getAttribute(Constant.USERNAME)!=null){

            model.addAttribute("newEmployee", new Employee());
            List<String> listOfGrades = new ArrayList<>();
            listOfGrades.add("G1");
            listOfGrades.add("G2");
            listOfGrades.add("G3");
            listOfGrades.add("G4");
            listOfGrades.add("G5");
            listOfGrades.add("G6");
            listOfGrades.add("G7");
            model.addAttribute("grade", listOfGrades);
            List<String> gender = new ArrayList<>();
            gender.add("Male");
            gender.add("Female");
            model.addAttribute("gender", gender);
            return Constant.EMPLOYEE_REGISTRATION;
        }else{
            return "redirect:index.html";
        }
    }

    @PostMapping(value = "addNewEmployee.html")
    public String addNewEmployee(@ModelAttribute("newEmployee") Employee employee, Model model, HttpSession session) {
        if(session.getAttribute(Constant.USERNAME)!=null){
            String message = employeeServices.addNewEmployee(employee);
            if (message.equals("true")) {
                model.addAttribute("successMessage", "New employee successfully created");
                return Constant.ADMIN_HOMEPAGE;
            } else {
                model.addAttribute("errorMessage", message);
                model.addAttribute("newEmployee",new Employee());
                model.addAttribute("newEmployee", new Employee());
                List<String> listOfGrades = new ArrayList<>();
                listOfGrades.add("G1");
                listOfGrades.add("G2");
                listOfGrades.add("G3");
                listOfGrades.add("G4");
                listOfGrades.add("G5");
                listOfGrades.add("G6");
                listOfGrades.add("G7");
                model.addAttribute("grade", listOfGrades);
                List<String> gender = new ArrayList<>();
                gender.add("Male");
                gender.add("Female");
                model.addAttribute("gender", gender);
                return Constant.EMPLOYEE_REGISTRATION;
            }
        }else{
            return "redirect:index.html";
        }
    }

    @PostMapping(value = "recordsPerPage.html")
    public String showRecordsPerPage(@ModelAttribute("employee")Employee employee, HttpSession session){
        session.removeAttribute(Constant.LIMIT);
        session.setAttribute(Constant.LIMIT,employee.getCount());
        return "redirect:showAllEmployees.html?next=null";
    }

    @GetMapping(value = "/showAllEmployees.html")
    public String showAllEmployees(@ModelAttribute("next")String next,@ModelAttribute("employee")Employee employee, Model model,HttpSession session){

        if(next.equals("null")){
            if(session.getAttribute(Constant.USERNAME)!=null){
                int offset = (int) session.getAttribute(Constant.OFFSET);
                int limit = (int) session.getAttribute(Constant.LIMIT);
                if(offset< employeeServices.getEmployeeCount()){
                    offset = 0;
                    session.removeAttribute(Constant.OFFSET);
                    session.setAttribute(Constant.OFFSET,offset);
                }
                List<Employee> allEmployees = employeeServices.getNextEmployees(offset,limit);
                model.addAttribute("allEmployees",allEmployees);
                model.addAttribute("employee", new Employee());
                return Constant.ALL_EMPLOYEES;
            }else{
                return "redirect:index.html";
            }
        }
        if(next.equals("true")){
            if(session.getAttribute(Constant.USERNAME)!=null){
                int offset = (int) session.getAttribute(Constant.OFFSET);
                int limit = (int) session.getAttribute(Constant.LIMIT);
                if(offset< employeeServices.getEmployeeCount()){
                    offset = offset + limit;
                    session.removeAttribute(Constant.OFFSET);
                    session.setAttribute(Constant.OFFSET,offset);
                }
                List<Employee> allEmployees = employeeServices.getNextEmployees(offset,limit);
                model.addAttribute("allEmployees",allEmployees);
                return Constant.ALL_EMPLOYEES;
            }else{
                return "redirect:index.html";
            }
        }
        if(next.equals("false")){
            if(session.getAttribute(Constant.USERNAME)!=null){
                int offset = (int) session.getAttribute(Constant.OFFSET);
                int limit = (int) session.getAttribute(Constant.LIMIT);
                if(offset>=0){
                    offset = offset - limit;
                    session.removeAttribute(Constant.OFFSET);
                    session.setAttribute(Constant.OFFSET,offset);
                }
                List<Employee> allEmployees = employeeServices.getNextEmployees(offset,limit);
                model.addAttribute("allEmployees",allEmployees);
                return Constant.ALL_EMPLOYEES;
            }else{
                return "redirect:index.html";
            }
        }
        return "";
    }
}
