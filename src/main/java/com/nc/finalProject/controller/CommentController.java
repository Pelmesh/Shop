package com.nc.finalProject.controller;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Template;
import com.nc.finalProject.service.CommentService;
import com.nc.finalProject.service.TemplateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CommentController {

    private static final Logger LOGGER = Logger.getLogger(CommentController.class);

    @Autowired
    private TemplateService templateService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/{template}")
    public String saveComment(@PathVariable Template template, Comment comment) {
        comment.setTemplate(template);
        commentService.create(comment);
        LOGGER.info("Comment created idCom:" + comment.getId());
        return "redirect:/tshirt/{template}";
    }

    @GetMapping("/comment/{template}")
    public String getComment(@PathVariable Template template, Model model) {
        model.addAttribute("comments", commentService.findByTemplate(template));
        return "comments";
    }

}
