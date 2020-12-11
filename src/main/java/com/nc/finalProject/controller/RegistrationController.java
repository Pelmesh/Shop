package com.nc.finalProject.controller;

import com.nc.finalProject.model.Role;
import com.nc.finalProject.model.User;
import com.nc.finalProject.model.enumModel.EnumRole;
import com.nc.finalProject.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegistration() {
        return "registration";
    }

    @PostMapping
    public String createUser(User user, Model model) {
        User userRepeat = userService.findByUsername(user.getUsername());
        if (userRepeat != null) {
            model.addAttribute("message", "User exist!");
            model.addAttribute("user", user);
            return "registration";
        }
        user.setRoles(Collections.singleton(new Role(1L, EnumRole.ROLE_USER.name())));
        userService.create(user);
        LOGGER.info("User created id:" + user.getId());
        return "redirect:/login";
    }

}
