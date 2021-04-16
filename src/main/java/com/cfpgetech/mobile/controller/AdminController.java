package com.cfpgetech.mobile.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {


    @RequestMapping(value = "/logon")
    public String logon(ModelMap map, HttpServletRequest req, HttpServletRequest resp) {

        // Méthode 1 : recupération username (principal = email)
        String user = req.getRemoteUser();
        System.out.println("Principal est : "+ user);

        if(user != null)
            return "accueil";
        else
            return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping(value= "/logout-success")
    public String getLogout() {
        return "redirect:/login";
    }
}
