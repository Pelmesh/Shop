package com.nc.finalProject.repo;

import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long>, JpaSpecificationExecutor<Template> {

    List<Template> findByAllSeeTrue();

    Page<Template> findByAllSeeTrue(Pageable pageable);

    Optional<Template> findById(Long id);

    Page<Template> findByUser(User user, Pageable pageable);

}
