package com.nc.finalProject.controller;

import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.TemplateFilter;
import com.nc.finalProject.model.User;
import com.nc.finalProject.model.enumModel.Gender;
import com.nc.finalProject.service.TemplateService;
import com.nc.finalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private TemplateService templateService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getMain(
            Model model,
            @PageableDefault(sort = {"id"}, size = 6, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", templateService.findByAllSeeTrue(pageable));
        model.addAttribute("url", "/");
        return "mainPage";
    }

    @GetMapping("filter")
    public String getFilter(TemplateFilter templateFilter,
                            Model model,
                            @PageableDefault(sort = {"id"}, size = 6, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", templateService.findAll(
                (Specification<Template>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (templateFilter.getName() != null && !templateFilter.getName().equals("")) {
                        predicates.add(
                                criteriaBuilder.and(
                                        criteriaBuilder.like(
                                                criteriaBuilder.lower(
                                                        root.get("name")),
                                                criteriaBuilder.lower(
                                                        criteriaBuilder.literal(
                                                                "%" + templateFilter.getName() + "%")))));
                    }
                    if (templateFilter.getAuthor() != null && !templateFilter.getAuthor().equals("")) {
                        User user = userService.findByUsername(templateFilter.getAuthor());
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("user"), user)));
                    }
                    if (templateFilter.isGenderFemale() && !templateFilter.isGenderMale()) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gender"), Gender.FEMALE)));
                    }
                    if (!templateFilter.isGenderFemale() && templateFilter.isGenderMale()) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gender"), Gender.MALE)));
                    }
                    if (templateFilter.getPriceStart() > 0 && templateFilter.getPriceStart() > 0) {
                        predicates.add(
                                criteriaBuilder.and(
                                        criteriaBuilder.between(root.get("price"),
                                                templateFilter.getPriceStart(), templateFilter.getPriceEnd())));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable));
        model.addAttribute("filter", templateFilter);
        model.addAttribute("url", "/filter");
        return "mainPage";
    }
}

