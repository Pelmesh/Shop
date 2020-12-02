package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.repo.CommentRepository;
import com.nc.finalProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByTshirt(Tshirt tshirt) {
        return commentRepository.findByTshirt(tshirt);
    }
}
