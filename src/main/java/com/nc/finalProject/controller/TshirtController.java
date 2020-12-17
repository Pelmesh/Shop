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
import com.nc.finalProject.service.CurrencyService;
import com.nc.finalProject.service.SizeService;
import com.nc.finalProject.service.TemplateService;
import com.nc.finalProject.service.TshirtService;
import com.nc.finalProject.util.CookieUtil;
import com.nc.finalProject.util.ValidatorUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CookieUtil cookieUtil;


    @GetMapping("create")
    public String getCreate() {
        return "create";
    }

    @PostMapping("create")
    public String saveTshirt(@AuthenticationPrincipal User user,
                             @Valid Template template,
                             BindingResult bindingResult,
                             Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ValidatorUtil.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("template", template);
            return "create";
        }
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
        return "redirect:/tshirt/" + template.getId();
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
        return pathSVG.substring(0, pathSVG.length() - 3) + "jpg";
    }


    @GetMapping("tshirt/{template}")
    public String getTshirt(@PathVariable Template template,
                            @CookieValue(value = "prevList", required = false) Cookie cookie,
                            Model model,
                            HttpServletResponse res,
                            @PageableDefault(sort = {"id"}, size = 5,
                                    direction = Sort.Direction.DESC) Pageable pageable) throws UnsupportedEncodingException {
        if (!template.isAllSee()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute("tShirt", template);
        List<Tshirt> list = template.getTshirts();
        List<Size> sizes = new ArrayList<>();
        for (Tshirt tshirt : list) {
            if (tshirt.getCount() > 0) sizes.add(sizeService.findByTshirts(tshirt));
        }
        model.addAttribute("sizes", sizes);
        model.addAttribute("page", commentService.findByTemplate(template, pageable));
        model.addAttribute("url", "/tshirt/" + template.getId());
        model.addAttribute("currency", currencyService.findAll());
        cookieUtil.saveInCookiesPreview(res, cookie, template);
        return "tShirt";
    }

}
