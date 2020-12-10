package com.nc.finalProject.controller.config;

import com.google.gson.Gson;
import com.nc.finalProject.model.Cart;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class CountCartInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cartList")) {
                        modelAndView.addObject("countCart", setCount(cookie));
                    }
                }
            } else {
                modelAndView.addObject("countCart", 0);
            }
        } else {
        }
    }

    private int setCount(Cookie cookie) throws UnsupportedEncodingException {
        String json = URLDecoder.decode(cookie.getValue(), "UTF-8");
        List<Cart> cartList = new Gson().fromJson(json, List.class);
        return cartList.size();
    }
}
