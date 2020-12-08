package com.nc.finalProject.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.Size;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.model.User;
import com.nc.finalProject.service.CartService;
import com.nc.finalProject.service.SizeService;
import com.nc.finalProject.service.TshirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private SizeService sizeService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String getCart(@AuthenticationPrincipal User user,
                          @CookieValue(value = "cartList", required = false) Cookie cookie,
                          Model model) throws UnsupportedEncodingException {
        if (user != null) {
            model.addAttribute("cartList", cartService.findAllByUser(user));
        } else {
            if (cookie != null) {
                String json = URLDecoder.decode(cookie.getValue(), "UTF-8");
                List<Cart> cartList = new Gson().fromJson(json, List.class);
                model.addAttribute("cartList", cartList);
            }
        }
        return "cart";
    }

    @PostMapping("{tShirt}")
    public String addToCart(@AuthenticationPrincipal User user,
                            @PathVariable Tshirt tShirt,
                            @RequestParam(name = "size") String sizeParam,
                            @CookieValue(value = "cartList", required = false) Cookie cookie,
                            HttpServletResponse res,
                            HttpServletRequest req) throws UnsupportedEncodingException {
        Size size = sizeService.findByTshirtAndSize(tShirt, sizeParam);
        Cart cart = new Cart(tShirt, size);
        if (user != null) {
            cart.setUser(user);
            cartService.create(cart);
        } else {
            saveInCookies(cart, res, req, cookie);
        }
        return "redirect:/cart";
    }

    private void saveInCookies(Cart cart,
                               HttpServletResponse res,
                               HttpServletRequest req,
                               Cookie cookie) throws UnsupportedEncodingException {
        String json;
        List<Cart> cartList = new ArrayList<>();
        if (cookie != null) {
            json = URLDecoder.decode(cookie.getValue(), "UTF-8");
            cartList = new Gson().fromJson(json, List.class);
        }
        cartList.add(cart);
        json = new Gson().toJson(cartList);
        cookie = new Cookie("cartList", URLEncoder.encode(json, "UTF-8"));
        cookie.setMaxAge(60);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    @GetMapping("tshirt/hay")
    public String addToCartd(@CookieValue(value = "cartList", required = false) Cookie cookieName) throws UnsupportedEncodingException {

        String s = URLDecoder.decode(cookieName.getValue(), "UTF-8");
        java.lang.reflect.Type type = new TypeToken<List<Cart>>(){}.getType();
        List<Cart> cartList = new Gson().fromJson(s, type);
        System.out.println(cartList.get(0).getTshirt().getTemplate().getPrice());
        return "tshirt";
    }
}

