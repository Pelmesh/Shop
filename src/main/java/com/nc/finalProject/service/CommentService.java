package com.nc.finalProject.service;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Comment create(Comment comment);

    Page<Comment> findByTemplate(Template template, Pageable pageable);

    void delete(Comment comment);

}
