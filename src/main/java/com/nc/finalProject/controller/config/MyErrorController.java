package com.nc.finalProject.controller.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/500";
    }

    @GetMapping("/error")
    public String handleError() {
        return "error";
    }

}
