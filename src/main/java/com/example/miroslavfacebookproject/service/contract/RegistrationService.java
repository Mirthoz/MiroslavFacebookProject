package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.RegisterDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public interface RegistrationService {

    ModelAndView registration(RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes);
}
