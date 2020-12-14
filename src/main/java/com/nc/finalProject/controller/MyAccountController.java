package com.nc.finalProject.controller;

import com.nc.finalProject.model.User;
import com.nc.finalProject.service.OrderService;
import com.nc.finalProject.service.TemplateService;
import com.nc.finalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("me")
public class MyAccountController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private TemplateService templateService;

    @GetMapping
    public String getMe(@AuthenticationPrincipal User user,
                        Model model,
                        @PageableDefault(sort = {"id"}, size = 4, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", orderService.findByUser(user, pageable));
        model.addAttribute("url", "/me");
        return "myPage";
    }

    @PostMapping
    public String saveData(@AuthenticationPrincipal User user,
                           @RequestParam String name,
                           @RequestParam String phoneNumber,
                           @RequestParam String address) {
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        userService.create(user);
        return "redirect:/me";
    }

    @GetMapping("myStyle")
    public String getMyStyle(@AuthenticationPrincipal User user,
                             Model model,
                             @PageableDefault(sort = {"id"}, size = 20,
                                     direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", templateService.findByUser(user, pageable));
        model.addAttribute("url", "/me/myStyle");
        return "main";
    }

}
