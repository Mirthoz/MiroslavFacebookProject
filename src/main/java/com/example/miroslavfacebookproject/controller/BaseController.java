package com.example.miroslavfacebookproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    protected ModelAndView send(String viewName) {
        return new ModelAndView(viewName);
    }

    protected ModelAndView redirect(String endpoint) {
        return new ModelAndView("redirect:/" + endpoint);
    }

    protected ModelAndView redirect(String endpoint, String objectName, Object object) {
        ModelAndView modelAndView = new ModelAndView("redirect:" + endpoint);
        modelAndView.addObject(objectName, object);
        return modelAndView;
    }
}
