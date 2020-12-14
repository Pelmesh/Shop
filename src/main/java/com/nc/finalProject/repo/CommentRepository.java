package com.nc.finalProject.repo;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByTemplate(Template template, Pageable pageable);

}
