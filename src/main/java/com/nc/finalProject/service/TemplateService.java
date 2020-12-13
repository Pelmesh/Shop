package com.nc.finalProject.service;

import com.nc.finalProject.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TemplateService {

    Template create(Template template);

    List<Template> findByAllSeeTrue();

    Page<Template> findByAllSeeTrue(Pageable pageable);

    Optional<Template> findById(Long id);

}
