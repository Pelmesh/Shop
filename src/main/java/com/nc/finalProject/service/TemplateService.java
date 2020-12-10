package com.nc.finalProject.service;

import com.nc.finalProject.model.Template;

import java.util.List;

public interface TemplateService {

    Template create(Template template);

    List<Template> findByAllSeeTrue();

}
