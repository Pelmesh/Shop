package com.nc.finalProject.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nc.finalProject.model.Cart;
import com.nc.finalProject.model.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CookieUtil {

    @Value("${maxAgeCookie}")
    private int ageCookie;


    public List<Cart> getCartListFromCookie(Cookie cookie) throws UnsupportedEncodingException {
        String json = URLDecoder.decode(cookie.getValue(), "UTF-8");
        java.lang.reflect.Type type = new TypeToken<List<Cart>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    public List<Template> getTemplateListFromCookie(Cookie cookie) throws UnsupportedEncodingException {
        String json = URLDecoder.decode(cookie.getValue(), "UTF-8");
        java.lang.reflect.Type type = new TypeToken<List<Template>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    public void saveInCookiesPreview(HttpServletResponse res,
                                     Cookie cookie,
                                     Template template) throws UnsupportedEncodingException {
        List<Template> listTemplate = new ArrayList<>();
        template.setTshirts(null);
        template.setUser(null);
        template.setTshirts(null);
        if (cookie != null) {
            listTemplate = getTemplateListFromCookie(cookie);
            listTemplate.add(template);
            if (listTemplate.size() > 2) {
                for (int i = listTemplate.size() - 1; i >= 1; i--) {
                    Collections.swap(listTemplate, i - 1, i);
                }
                setPreviewCookies(res, new Gson().toJson(listTemplate.subList(0, 4)));
            } else if (listTemplate.size() == 2) {
                listTemplate.add(template);
                Collections.swap(listTemplate, 1, 0);
                setPreviewCookies(res, new Gson().toJson(listTemplate));
            }
        } else {
            listTemplate.add(template);
            setPreviewCookies(res, new Gson().toJson(listTemplate));
        }

    }

    public void setPreviewCookies(HttpServletResponse res, String json) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie("prevList", URLEncoder.encode(json, "UTF-8"));
        cookie.setMaxAge(ageCookie);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public void setCartCookie(HttpServletResponse res, String json) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie("cartList", URLEncoder.encode(json, "UTF-8"));
        cookie.setMaxAge(ageCookie);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public void removeCookie(HttpServletResponse res, String name) {
        Cookie removeCookie = new Cookie(name, "");
        removeCookie.setMaxAge(0);
        removeCookie.setPath("/");
        res.addCookie(removeCookie);
    }
}