package com.nc.finalProject.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.Item;
import com.nc.finalProject.model.Order;
import com.nc.finalProject.model.Status;
import com.nc.finalProject.model.User;
import com.nc.finalProject.service.CartService;
import com.nc.finalProject.service.ItemService;
import com.nc.finalProject.service.OrderService;
import com.nc.finalProject.service.TshirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private TshirtService tshirtService;

    @PostMapping
    public String saveOrder(@AuthenticationPrincipal User user,
                            @CookieValue(value = "cartList", required = false) Cookie cookie,
                            Order order,
                            HttpServletResponse res) throws UnsupportedEncodingException {
        List<Item> itemList;
        order.setStatus(Status.PROCESSED.name());
        if (user != null) {
            order.setUser(user);
            orderService.create(order);
            itemList = getListItemsFromCart(user, order);
        } else {
            orderService.create(order);
            itemList = getListItemsFromCookies(order, cookie);
            Cookie removeCookie = new Cookie("cartList", "");
            removeCookie.setMaxAge(0);
            res.addCookie(removeCookie);
        }
        itemService.create(itemList);
        return "redirect:/";
    }

    private List<Item> getListItemsFromCart(User user, Order order) {
        List<Cart> cartList = cartService.findAllByUser(user);
        cartService.deleteAll(cartList);
        return getItem(cartList, order);
    }

    private List<Item> getListItemsFromCookies(Order order, Cookie cookie) throws UnsupportedEncodingException {
        String json = URLDecoder.decode(cookie.getValue(), "UTF-8");
        java.lang.reflect.Type type = new TypeToken<List<Cart>>() {}.getType();
        List<Cart> cartList = new Gson().fromJson(json, type);
        return getItem(cartList, order);
    }

    private List<Item> getItem(List<Cart> cartList, Order order) {
        List<Item> itemList = new ArrayList<>();
        for (Cart cart : cartList) {
            itemList.add(new Item(tshirtService.findById((cart.getTshirt().getId())).get(), order));
        }
        return itemList;
    }

}
