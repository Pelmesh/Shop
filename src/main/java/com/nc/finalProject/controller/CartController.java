package com.nc.finalProject.controller;

import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.model.User;
import com.nc.finalProject.service.CartService;
import com.nc.finalProject.service.TshirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private TshirtService tshirtService;

    @Autowired
    private CartService cartService;

    @PostMapping("tshirt/{tShirt}")
    public String addToCart(@PathVariable Tshirt tShirt,
                            @AuthenticationPrincipal User user,
                            Cart cart,
                            Model model,
                            HttpServletRequest req) {
        cart.setTshirt(tShirt);
        if (user != null){
            saveInCart(cart,user);
        } else {
            saveInSession(cart, req);
        }
        return "redirect:/";
    }

    private void saveInCart(Cart cart, User user) {
        cart.setUser(user);;
        cartService.create(cart);
    }

    private void saveInSession(Cart cart, HttpServletRequest req) {
        HttpSession session = req.getSession();
        try {
            List<Cart> list = new ArrayList<>();
            if (session.getAttribute("cartList") == null) {
                list.add(cart);
                session.setAttribute("cartList", list);
            } else {
                list = (List<Cart>) session.getAttribute("cartList");
                list.add(cart);
                session.removeAttribute("cartList");
                session.setAttribute("cartList", list);
            }
        }catch (Exception e){
            session.removeAttribute("cartList");
        }
    }

    @GetMapping("tshirt/hay")
    public String addToCartd(HttpServletRequest req) {
        HttpSession session = req.getSession();
        List<Cart> list = (List<Cart>) session.getAttribute("cartList");
        return "tshirt";
    }
}
