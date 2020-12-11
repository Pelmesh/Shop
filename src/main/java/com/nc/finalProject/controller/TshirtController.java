package com.nc.finalProject.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nc.finalProject.model.Size;
import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.model.User;
import com.nc.finalProject.model.enumModel.EnumSize;
import com.nc.finalProject.model.enumModel.Gender;
import com.nc.finalProject.service.CommentService;
import com.nc.finalProject.service.SizeService;
import com.nc.finalProject.service.TemplateService;
import com.nc.finalProject.service.TshirtService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class TshirtController {

    private static final Logger LOGGER = Logger.getLogger(TshirtController.class);

    @Autowired
    private TshirtService tShirtService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private CommentService commentService;


    @GetMapping("create")
    public String getCreate() {
        return "create";
    }

    @PostMapping("create")
    public String saveTshirt(@AuthenticationPrincipal User user, Template template) throws IOException {
        template.setUrl(addImage(template.getUrl()));
        if (user != null) {
            template.setUser(user);
        } else {
            template.setAllSee(true);
        }
        template.setPrice(30.0);
        template.setDiscountPrice(25.0);
        template.setDiscount(false);
        templateService.create(template);
        LOGGER.info("Template created id:" + template.getId());
        setSize(template);
        return "create";
    }

    private void setSize(Template template) {
        if (template.getGender().equals(Gender.FEMALE.name())) {
            tShirtService.create(new Tshirt(template, sizeService.findBySize(EnumSize.XS.name()), 20));
            tShirtService.create(new Tshirt(template, sizeService.findBySize(EnumSize.S.name()), 20));
            tShirtService.create(new Tshirt(template, sizeService.findBySize(EnumSize.M.name()), 20));
            tShirtService.create(new Tshirt(template, sizeService.findBySize(EnumSize.L.name()), 20));
        } else {
            tShirtService.create(new Tshirt(template, sizeService.findBySize(EnumSize.S.name()), 20));
            tShirtService.create(new Tshirt(template, sizeService.findBySize(EnumSize.M.name()), 20));
            tShirtService.create(new Tshirt(template, sizeService.findBySize(EnumSize.L.name()), 20));
            tShirtService.create(new Tshirt(template, sizeService.findBySize(EnumSize.XL.name()), 20));
        }
    }

    private String addImage(String svg) throws IOException {
        FileWriter writer = new FileWriter("file.png", false);
        writer.append(svg);
        writer.flush();
        return addToCloud();
    }

    private String addToCloud() throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "itr",
                "api_key", "224226883725776",
                "api_secret", "b1t0r9MrMI4YHq5oeCQs3avCsq4"));
        Map uploadRezult = cloudinary.uploader().upload("file.png", ObjectUtils.emptyMap());
        String pathSVG = uploadRezult.get("secure_url").toString();
        return pathSVG.substring(0, pathSVG.length() - 3) + "png";
    }


    @GetMapping("tshirt/{template}")
    public String getTshirt(@PathVariable Template template, Model model, HttpServletResponse res) {
        if (!template.isAllSee()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute("tShirt", template);
        List<Tshirt> list = template.getTshirts();
        List<Size> sizes = new ArrayList<>();
        for (Tshirt tshirt : list) {
            sizes.add(sizeService.findByTshirts(tshirt));
        }
        model.addAttribute("sizes", sizes);
        model.addAttribute("comments", commentService.findByTemplate(template));
        return "tShirt";
    }
}
