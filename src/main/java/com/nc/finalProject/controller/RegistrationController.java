package com.nc.finalProject.controller;

import com.nc.finalProject.model.Role;
import com.nc.finalProject.model.User;
import com.nc.finalProject.model.enumModel.EnumRole;
import com.nc.finalProject.service.UserService;
import com.nc.finalProject.util.MailSenderUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.UUID;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MailSenderUtil mailSender;

    @Value("${host.name}")
    private String host;


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
        userRepeat = userService.findByEmail(user.getEmail());
        if (userRepeat != null) {
            model.addAttribute("message", "Email exist!");
            model.addAttribute("user", user);
            return "registration";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("message", "Passwords do not match!");
            model.addAttribute("user", user);
            return "registration";
        }
        user.setRoles(Collections.singleton(new Role(1L, EnumRole.ROLE_USER.name())));
        user.setActive(false);
        user.setActivateCode(UUID.randomUUID().toString());
        userService.create(user);
        mailSender.send(user.getEmail(), "Activation code", getCodeForMail(user.getActivateCode()));
        LOGGER.info("User created id:" + user.getId());
        return "redirect:/login";
    }

    private String getCodeForMail(String activateCode) {
        return host + "registration/" + activateCode;
    }

    @GetMapping("{code}")
    public String activate(@PathVariable String code) {
        User user = userService.findByActivateCode(code);
        if (user != null) {
            user.setActivateCode(null);
            user.setActive(true);
            userService.create(user);
        }
        return "redirect:/login";
    }
}
