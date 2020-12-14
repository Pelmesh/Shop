package com.nc.finalProject.service;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment);

    Page<Comment> findByTemplate(Template template, Pageable pageable);

}
