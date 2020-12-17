package com.nc.finalProject.controller;

import com.google.gson.Gson;
import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.Template;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.model.User;
import com.nc.finalProject.service.CartService;
import com.nc.finalProject.service.CurrencyService;
import com.nc.finalProject.service.TshirtService;
import com.nc.finalProject.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private TshirtService tshirtService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CookieUtil cookieUtil;

    @GetMapping
    public String getCart(@AuthenticationPrincipal User user,
                          @CookieValue(value = "cartList", required = false) Cookie cookie,
                          HttpServletResponse res,
                          Model model) throws UnsupportedEncodingException {
        List<Cart> cartList = new ArrayList<>();
        if (user != null && cookie == null) {
            cartList = cartService.findAllByUser(user);
        } else if (user != null && cookie != null) {
            List<Cart> cartListFromCookie = cookieUtil.getCartListFromCookie(cookie);
            for (Cart cart : cartListFromCookie) {
                cartService.create(new Cart(user, tshirtService.findById(cart.getTshirt().getId()).get()));
            }
            cartList = cartService.findAllByUser(user);
            cookieUtil.removeCookie(res, "cartList");
        } else if (cookie != null) {
            cartList = cookieUtil.getCartListFromCookie(cookie);
        }
        if (!cartList.isEmpty()) model.addAttribute("cartList", cartList);
        model.addAttribute("currency", currencyService.findAll());
        return "cart";
    }

    @PostMapping("{template}")
    public String addToCart(@AuthenticationPrincipal User user,
                            @PathVariable Template template,
                            @RequestParam(name = "size") String sizeParam,
                            @CookieValue(value = "cartList", required = false) Cookie cookie,
                            HttpServletResponse res) throws UnsupportedEncodingException {
        Tshirt tShirt = tshirtService.findByTemplateAndSize_Size(template, sizeParam);
        Cart cart = new Cart(tShirt);
        if (user != null) {
            cart.setUser(user);
            cartService.create(cart);
        } else {
            saveInCookies(cart, res, cookie);
        }
        return "redirect:/cart";
    }

    private void saveInCookies(Cart cart,
                               HttpServletResponse res,
                               Cookie cookie) throws UnsupportedEncodingException {
        List<Cart> cartList = new ArrayList<>();
        if (cookie != null) {
            cartList = cookieUtil.getCartListFromCookie(cookie);
        }
        cartList.add(cart);
        for (Cart value : cartList) {
            value.getTshirt().getTemplate().setTshirts(null);
            value.getTshirt().getTemplate().setUser(null);
            value.getTshirt().getSize().setTshirts(null);
        }
        cookieUtil.setCartCookie(res, new Gson().toJson(cartList));
    }

    @DeleteMapping("{id}")
    public String deleteCart(@PathVariable Long id,
                             @CookieValue(value = "cartList", required = false) Cookie cookie,
                             @AuthenticationPrincipal User user,
                             HttpServletResponse res) throws UnsupportedEncodingException {
        if (user == null) {
            List<Cart> cartList = cookieUtil.getCartListFromCookie(cookie);
            cartList.removeIf(cart -> cart.getTshirt().getId().equals(id));
            cookieUtil.setCartCookie(res, new Gson().toJson(cartList));
        } else {
            cartService.delete(cartService.findTopByUserAndTshirt_Id(user, id));
        }
        return "redirect:/cart";
    }


}

