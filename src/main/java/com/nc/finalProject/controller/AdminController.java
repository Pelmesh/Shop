package com.nc.finalProject.controller;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.User;
import com.nc.finalProject.service.CommentService;
import com.nc.finalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String getAdminPanel(Model model,
                                @PageableDefault(sort = {"id"}, size = 30,
                                        direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", userService.findAll(pageable));
        model.addAttribute("url", "/");
        return "admin";
    }

    @PutMapping("{user}")
    public String updateUser(User user, @RequestParam boolean isActive) {
        user.setActive(isActive);
        userService.create(user);
        return "redirect:/admin";
    }

    @DeleteMapping("{comment}")
    public String deleteComment(@PathVariable Comment comment) {
        commentService.delete(comment);
        return "redirect:/";
    }

}
