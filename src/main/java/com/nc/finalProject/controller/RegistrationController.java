package com.nc.finalProject.controller;

import com.nc.finalProject.model.User;
import com.nc.finalProject.model.enumModel.EnumRole;
import com.nc.finalProject.service.UserService;
import com.nc.finalProject.util.MailSenderUtil;
import com.nc.finalProject.util.ValidatorUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSenderUtil mailSender;

    @Value("${host.name}")
    private String host;


    @GetMapping
    public String getRegistration() {
        return "registration";
    }

    @PostMapping
    public String createUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ValidatorUtil.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("user", user);
            return "registration";
        }
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
        user.setRole(EnumRole.USER.name());
        user.setActive(false);
        user.setActivateCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        if (!mailSender.send(user.getEmail(), "Activation code", getCodeForMail(user.getActivateCode()))){
            userService.delete(user);
            model.addAttribute("message",
                    "Incorrect email!");
            return "registration";
        }
        LOGGER.info("User created id:" + user.getId());
        redirectAttributes.addFlashAttribute("messageSuccess",
                "Check your email!");
        return "redirect:/login";
    }

    private String getCodeForMail(String activateCode) {
        return host + "registration/" + activateCode;
    }

    @GetMapping("{code}")
    public String activate(@PathVariable String code, RedirectAttributes redirectAttributes) {
        User user = userService.findByActivateCode(code);
        if (user != null) {
            user.setActivateCode(null);
            user.setActive(true);
            userService.create(user);
            redirectAttributes.addFlashAttribute("messageSuccess",
                    "Account activated!");
        }
        return "redirect:/login";
    }
}
