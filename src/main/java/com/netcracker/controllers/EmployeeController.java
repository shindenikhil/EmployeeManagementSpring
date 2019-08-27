package com.netcracker.controllers;


import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeServices;
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

    @RequestMapping("/adminHomepage.html")
    public String getAdminHomepage(Model model,HttpSession session){
        if(session.getAttribute("username")!=null) {
            return "adminHomepage";
        }else{
            return "redirect:index.html";
        }
    }

    @RequestMapping("/searchEmployee.html")
    public String getEmployeeSearchForm(Model model,HttpSession session){
        if(session.getAttribute("username")!=null){
            model.addAttribute("employee", new Employee());
            return "searchEmployee";
        }else{
            return "redirect:index.html";
        }
    }

    @RequestMapping(value = "submitEmployeeSearchForm.html",method = RequestMethod.POST)
    public String submitEmployeeSearchForm(@ModelAttribute("employee")Employee employee, Model model, HttpSession session){
        if(session.getAttribute("username")!=null){

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
                return "editEmployee";
            }else{
                model.addAttribute("errorMessage","Employee not found");
                model.addAttribute("employee",new Employee());
                return "searchEmployee";

            }
        }else{
            return "redirect:index.html";
        }
    }

    @RequestMapping(value = "updateEmployee.html",method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute("retrievedEmployee")Employee employee,Model model,HttpSession session){
        if(session.getAttribute("username")!=null){
        String message = employeeServices.updateEmployee(employee);
            if(message.equals("true")){
                model.addAttribute("successMessage","Employee updated successfully");
                model.addAttribute("employee",new Employee());
                return "searchEmployee";
            }else{
                model.addAttribute("errorMessage",message);
                model.addAttribute("employee",new Employee());
                return "searchEmployee";
            }
        }else{
            return "redirect:index.html";
        }
    }

    @RequestMapping(value = "getEmployeeRegistrationForm.html", method = RequestMethod.POST)
    public String getEmployeeRegistrationForm(Model model,HttpSession session) {
        if(session.getAttribute("username")!=null){

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
            return "employeeRegistration";
        }else{
            return "redirect:index.html";
        }
    }

    @RequestMapping(value = "addNewEmployee.html", method = RequestMethod.POST)
    public String addNewEmployee(@ModelAttribute("newEmployee") Employee employee, Model model, HttpSession session) {
        if(session.getAttribute("username")!=null){
            String message = employeeServices.addNewEmployee(employee);
            if (message.equals("true")) {
                model.addAttribute("successMessage", "New employee successfully created");
                return "adminHomepage";
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
                return "employeeRegistration";
            }
        }else{
            return "redirect:index.html";
        }
    }

    @RequestMapping(value = "recordsPerPage.html",method = RequestMethod.POST)
    public String showRecordsPerPage(@ModelAttribute("employee")Employee employee, HttpSession session){
        session.removeAttribute("limit");
        session.setAttribute("limit",employee.getCount());
        return "redirect:showAllEmployees.html?next=null";
    }

    @RequestMapping(value = "/showAllEmployees.html",method = RequestMethod.GET)
    public String showAllEmployees(@ModelAttribute("next")String next,@ModelAttribute("employee")Employee employee, Model model,HttpSession session){

        if(next.equals("null")){
            if(session.getAttribute("username")!=null){
                int offset = (int) session.getAttribute("offset");
                int limit = (int) session.getAttribute("limit");
                if(offset< employeeServices.getEmployeeCount()){
                    offset = 0;
                    session.removeAttribute("offset");
                    session.setAttribute("offset",offset);
                }
                List<Employee> allEmployees = employeeServices.getNextEmployees(offset,limit);
                model.addAttribute("allEmployees",allEmployees);
                model.addAttribute("employee", new Employee());
                return "allEmployees";
            }else{
                return "redirect:index.html";
            }
        }
        if(next.equals("true")){
            if(session.getAttribute("username")!=null){
                int offset = (int) session.getAttribute("offset");
                int limit = (int) session.getAttribute("limit");
                if(offset< employeeServices.getEmployeeCount()){
                    offset = offset + limit;
                    session.removeAttribute("offset");
                    session.setAttribute("offset",offset);
                }
                List<Employee> allEmployees = employeeServices.getNextEmployees(offset,limit);
                model.addAttribute("allEmployees",allEmployees);
                return "allEmployees";
            }else{
                return "redirect:index.html";
            }
        }
        if(next.equals("false")){
            if(session.getAttribute("username")!=null){
                int offset = (int) session.getAttribute("offset");
                int limit = (int) session.getAttribute("limit");
                if(offset>=0){
                    offset = offset - limit;
                    session.removeAttribute("offset");
                    session.setAttribute("offset",offset);
                }
                List<Employee> allEmployees = employeeServices.getNextEmployees(offset,limit);
                model.addAttribute("allEmployees",allEmployees);
                return "allEmployees";
            }else{
                return "redirect:index.html";
            }
        }
        return "";
    }
}
