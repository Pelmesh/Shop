package com.nc.finalProject.repo;

import com.nc.finalProject.model.Comment;
import com.nc.finalProject.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTshirt(Tshirt tshirt);

}
