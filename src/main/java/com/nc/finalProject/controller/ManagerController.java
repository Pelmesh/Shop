package com.nc.finalProject.controller;

import com.nc.finalProject.model.Order;
import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.service.OrderService;
import com.nc.finalProject.service.TemplateService;
import com.nc.finalProject.service.TshirtService;
import com.nc.finalProject.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("manager")
@PreAuthorize("hasAuthority('MANAGER')")
public class ManagerController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private TshirtService tshirtService;

    @Autowired
    private TemplateService templateService;

    @GetMapping
    public String getManager(Model model,
                             @PageableDefault(sort = {"id"}, size = 30,
                                     direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", orderService.findAll(pageable));
        model.addAttribute("url", "/manager");
        return "manager";
    }

    @PutMapping("{order}")
    public String updateOrder(@Valid Order order, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ValidatorUtil.getErrors(bindingResult);
            redirectAttributes.mergeAttributes(errorsMap);
            redirectAttributes.addFlashAttribute("messageError", "Check data!");
            return "redirect:/manager";
        }
        orderService.create(order);
        redirectAttributes.addFlashAttribute("messageSuccess", "Saved!");
        return "redirect:/manager";
    }

    @GetMapping("{order}")
    public String getOrder(@PathVariable Order order, Model model) {
        model.addAttribute("order", order);
        return "orderInfo";
    }

    @GetMapping("/tshirts")
    public String getTshirts(Model model,
                             @PageableDefault(sort = {"id"}, size = 30,
                                     direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", tshirtService.findAll(pageable));
        model.addAttribute("url", "/manager/tshirts");
        return "tshirtList";
    }

    @PutMapping("/tshirts/{tshirt}")
    public String updateTshirt(@Valid Tshirt tshirt,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ValidatorUtil.getErrors(bindingResult);
            redirectAttributes.mergeAttributes(errorsMap);
            redirectAttributes.addFlashAttribute("messageError", "Check data!");
            return "redirect:/manager/tshirts";
        }
        tshirtService.create(tshirt);
        return "redirect:/manager/tshirts";
    }

    @GetMapping("/templates")
    public String updateTshirt(Model model,
                               @PageableDefault(sort = {"id"}, size = 30,
                                       direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", templateService.findAll(pageable));
        model.addAttribute("url", "/manager/templates");
        return "templates";
    }

    @PutMapping("/templates/{template}")
    public String updateTemplate(@Valid Template template, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ValidatorUtil.getErrors(bindingResult);
            redirectAttributes.mergeAttributes(errorsMap);
            redirectAttributes.addFlashAttribute("messageError", "Check data!");
            return "redirect:/manager/templates";
        }
        templateService.create(template);
        redirectAttributes.addFlashAttribute("messageSuccess", "Saved!");
        return "redirect:/manager/templates";
    }
}
