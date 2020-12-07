package com.nc.finalProject.controller;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.service.CommentService;
import com.nc.finalProject.service.TshirtService;
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
    private TshirtService tshirtService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/{tShirt}")
    public String saveComment(@PathVariable Tshirt tShirt, Comment comment) {
        comment.setTshirt(tShirt);
        commentService.create(comment);
        LOGGER.info("Comment created idCom:" + comment.getId());
        return "redirect:/tshirt/{tShirt}";
    }

    @GetMapping("/comment/{tShirt}")
    public String getComment(@PathVariable Tshirt tShirt, Model model) {
        model.addAttribute("comments", commentService.findByTshirt(tShirt));
        return "comments";
    }

}
