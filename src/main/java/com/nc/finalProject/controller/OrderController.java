package com.nc.finalProject.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.Item;
import com.nc.finalProject.model.Order;
import com.nc.finalProject.model.Tshirt;
import com.nc.finalProject.model.User;
import com.nc.finalProject.model.enumModel.Status;
import com.nc.finalProject.service.CartService;
import com.nc.finalProject.service.ItemService;
import com.nc.finalProject.service.OrderService;
import com.nc.finalProject.service.TshirtService;
import com.nc.finalProject.util.CookieUtil;
import com.nc.finalProject.util.MailSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private MailSenderUtil mailSender;

    @Value("${host.name}")
    private String host;

    @PostMapping
    public String saveOrder(@AuthenticationPrincipal User user,
                            @CookieValue(value = "cartList", required = false) Cookie cookie,
                            Order order,
                            RedirectAttributes redirectAttributes,
                            HttpServletResponse res) throws UnsupportedEncodingException {
        List<Item> itemList;
        order.setStatus(Status.PROCESSED.name());
        if (user != null) {
            if (!chekItem(user)) {
                redirectAttributes.addFlashAttribute("message",
                        "There are items in the cart that are out of stock");
                return "redirect:/cart";
            }
            order.setUser(user);
            orderService.create(order);
            itemList = getListItemsFromCart(user, order);
        } else {
            if (!chekItem(cookie)) {
                redirectAttributes.addFlashAttribute("message",
                        "There are items in the cart that are out of stock");
                return "redirect:/cart";
            }
            orderService.create(order);
            itemList = getListItemsFromCookies(order, cookie);
            Cookie removeCookie = new Cookie("cartList", "");
            removeCookie.setMaxAge(0);
            res.addCookie(removeCookie);
        }
        itemService.create(itemList);
        takeOne(itemList);
        sendMail(itemList, order);
        redirectAttributes.addFlashAttribute("messageSuccess",
                "Order is processed");
        return "redirect:/";
    }

    private void sendMail(List<Item> itemList, Order order) {
        String message = "Order " + order.getId() + "\n";
        for (Item item : itemList) {
            message += host + "tshirt/" + item.getTshirt().getTemplate().getId() + "\n";
        }
        mailSender.send(order.getMail(), "Order", message);
    }

    private void takeOne(List<Item> itemList) {
        for (Item item : itemList) {
            Tshirt tshirt = item.getTshirt();
            tshirt.setCount(tshirt.getCount() - 1);
            tshirtService.create(tshirt);
        }
    }

    private boolean chekItem(User user) {
        List<Cart> cartList = cartService.findAllByUser(user);
        for (Cart cart : cartList) {
            if (cart.getTshirt().getCount() < 1) return false;
        }
        return true;
    }

    private boolean chekItem(Cookie cookie) throws UnsupportedEncodingException {
        List<Cart> cartList = cookieUtil.getCartListFromCookie(cookie);
        for (Cart cart : cartList) {
            if (cart.getTshirt().getCount() < 1) return false;
        }
        return true;
    }

    private List<Item> getListItemsFromCart(User user, Order order) {
        List<Cart> cartList = cartService.findAllByUser(user);
        cartService.deleteAll(cartList);
        return getItem(cartList, order);
    }

    private List<Item> getListItemsFromCookies(Order order, Cookie cookie) throws UnsupportedEncodingException {
        return getItem(cookieUtil.getCartListFromCookie(cookie), order);
    }

    private List<Item> getItem(List<Cart> cartList, Order order) {
        List<Item> itemList = new ArrayList<>();
        for (Cart cart : cartList) {
            itemList.add(new Item(tshirtService.findById((cart.getTshirt().getId())).get(), order));
        }
        return itemList;
    }

}
