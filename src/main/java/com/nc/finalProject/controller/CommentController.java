package com.nc.finalProject.controller;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.User;
import com.nc.finalProject.service.CommentService;
import com.nc.finalProject.service.TemplateService;
import com.nc.finalProject.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping
public class CommentController {

    private static final Logger LOGGER = Logger.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private UserService userService;

    @MessageMapping("/message/{id}")
    @SendTo("/chat/messages/{id}")
    public Comment getMessages(@DestinationVariable Long id,
                               @RequestBody Comment comment) {
        Optional<User> user = userService.findById(comment.getId());
        if (!user.isPresent()) {
            return null;
        }
        Template template = templateService.findById(id).get();
        comment.setId(null);
        comment.setAuthor(user.get());
        comment.setTemplate(template);
        commentService.create(comment);
        LOGGER.info("Comment created idCom:" + comment.getId());
        comment.getAuthor().setTemplates(null);
        comment.getTemplate().setUser(null);
        comment.getTemplate().setTshirts(null);
        return comment;
    }

}