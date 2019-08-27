package com.netcracker.controllers;

import com.netcracker.dto.Admin;
import com.netcracker.services.AdminServices;
import com.netcracker.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    @GetMapping(value = "/index.html")
    public String getLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return Constant.LOGIN_PAGE;
    }

    @PostMapping(value = "/verifyAdmin.html")
    public String verifyAdmin(@ModelAttribute("admin") Admin admin, Model model, HttpSession session) {

        if (adminServices.getAdminByUsernameAndPassword(admin) != null) {
            session.setAttribute("username", admin.getUsername());
            session.setAttribute("offset",0);
            session.setAttribute("limit",5);
            return "adminHomepage";
        } else {
            model.addAttribute("message", "Invalid credentials");
            model.addAttribute("admin", new Admin());
            return "login";
        }


    }
    @PostMapping(value = "/logout.html")
    public String logoutAdmin(Model model, HttpSession session) {
        session.invalidate();
        model.addAttribute("logoutMessage", "Successfully logged out");
        model.addAttribute("admin", new Admin());
        return "login";
    }
}
