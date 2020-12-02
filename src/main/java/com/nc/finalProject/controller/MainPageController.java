package com.nc.finalProject.controller;

import com.nc.finalProject.service.TshirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @Autowired
    private TshirtService tShirtService;

    @GetMapping
    public String getMain(Model model){
        model.addAttribute("tShirts", tShirtService.findAll());
        return "main";
    }

}
