package com.nc.finalProject.service;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Tshirt;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment);

    List<Comment> findByTshirt(Tshirt tshirt);
}
