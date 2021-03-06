package com.nc.finalProject.service;

import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface TemplateService {

    Template create(Template template);

    List<Template> findByAllSeeTrue();

    Page<Template> findByAllSeeTrue(Pageable pageable);

    Optional<Template> findById(Long id);

    Page<Template>  findByUser(User user, Pageable pageable);

    Page<Template> findAll(Pageable pageable);

    Page<Template> findAll(Specification<Template> templateSpecification, Pageable pageable);

}
