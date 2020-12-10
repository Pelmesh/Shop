package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Template;
import com.nc.finalProject.repo.TemplateRepository;
import com.nc.finalProject.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public Template create(Template template) {
        return templateRepository.save(template);
    }

    @Override
    public List<Template> findByAllSeeTrue() {
        return templateRepository.findByAllSeeTrue();
    }
}
