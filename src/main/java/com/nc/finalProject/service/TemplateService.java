package com.nc.finalProject.service;

import com.nc.finalProject.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TemplateService {

    Template create(Template template);

    List<Template> findByAllSeeTrue();

    Page<Template> findByAllSeeTrue(Pageable pageable);


}
