package com.nc.finalProject.repo;

import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TshirtRepository extends JpaRepository<Tshirt, Long> {

    List<Tshirt> findByTemplateAllSee(boolean b);

    Tshirt findByTemplateAndSize_Size(Template template, String size);

}
