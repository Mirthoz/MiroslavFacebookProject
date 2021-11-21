package com.example.miroslavfacebookproject.service.contract;

import org.springframework.web.servlet.ModelAndView;

public interface ResetPasswordService {
    ModelAndView resetPassword(String code, ModelAndView modelAndView);
}
