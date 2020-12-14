package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Template;
import com.nc.finalProject.repo.CommentRepository;
import com.nc.finalProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> findByTemplate(Template template, Pageable pageable) {
        return commentRepository.findByTemplate(template, pageable);
    }
}
