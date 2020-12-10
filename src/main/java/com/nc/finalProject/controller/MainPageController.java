package com.nc.finalProject.controller;

import com.nc.finalProject.model.Template;
import com.nc.finalProject.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private TemplateService templateService;

    @GetMapping
    public String getMain(Model model){
        List<Template> list = templateService.findByAllSeeTrue();
        model.addAttribute("tShirts", list);
        return "main";
    }

}
