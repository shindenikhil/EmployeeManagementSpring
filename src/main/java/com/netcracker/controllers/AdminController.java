package com.netcracker.controllers;

import com.netcracker.dto.Admin;
import com.netcracker.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    @RequestMapping(value = "/index.html",method = RequestMethod.GET)
    public String getLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "login";
    }

    @RequestMapping(value = "/verifyAdmin.html", method = RequestMethod.POST)
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

    @RequestMapping(value = "/logout.html",method = RequestMethod.POST)
    public String logoutAdmin(Model model, HttpSession session) {
        session.invalidate();
        model.addAttribute("logoutMessage", "Successfully logged out");
        model.addAttribute("admin", new Admin());
        return "login";
    }
}
