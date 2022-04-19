package com.example.securitythymeleaf.controllers;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginConttroller {

    @GetMapping("/login")
    public String mostrarFormLogin(@RequestParam(value = "error", defaultValue = "false") boolean loginError   ) {
        return "formLogin";
    }

    @GetMapping("/logout")
    public String fazerLogout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler =  new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login";
    }
}
