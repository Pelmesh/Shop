package com.nc.finalProject.controller;

import com.nc.finalProject.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @Autowired
    private TemplateService templateService;

    @GetMapping
    public String getMain(
            Model model,
            @PageableDefault(sort = {"id"}, size = 8, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", templateService.findByAllSeeTrue(pageable));
        model.addAttribute("url", "/");
        return "main";
    }

}
