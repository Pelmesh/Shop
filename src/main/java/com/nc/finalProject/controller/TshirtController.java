package com.nc.finalProject.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.model.User;
import com.nc.finalProject.service.CommentService;
import com.nc.finalProject.service.TshirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping
public class TshirtController {
    @Autowired
    private TshirtService tShirtService;

    @Autowired
    private CommentService commentService;

    @GetMapping("create")
    public String getCreate(){
        return "create";
    }

    @PostMapping("create")
    public String saveTshirt(@AuthenticationPrincipal User user, Tshirt tShirt) throws IOException {
        tShirt.setUrl(addImage(tShirt.getUrl()));
        if (user != null){
            tShirt.setUser(user);
        }
        tShirt.setPrice(30.0);
        tShirt.setDiscount(false);
        tShirtService.create(tShirt);
        return "create";
    }

    private String addImage(String svg) throws IOException {
        FileWriter writer = new FileWriter("file.png", false);
        writer.append(svg);
        writer.flush();
        return addToCloud();
    }

    private String addToCloud() throws IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name","itr",
                "api_key","224226883725776",
                "api_secret", "b1t0r9MrMI4YHq5oeCQs3avCsq4"));
        Map uploadRezult = cloudinary.uploader().upload("file.png", ObjectUtils.emptyMap());
        String pathSVG = uploadRezult.get("secure_url").toString();
        return pathSVG.substring(0, pathSVG.length()-3)+"png";
    }

    @GetMapping("tshirt/{tShirt}")
    public String getTshirt(@PathVariable Tshirt tShirt, Model model){
        model.addAttribute("tShirt",tShirt);
        model.addAttribute("comments", commentService.findByTshirt(tShirt));
        return "tShirt";
    }
}
