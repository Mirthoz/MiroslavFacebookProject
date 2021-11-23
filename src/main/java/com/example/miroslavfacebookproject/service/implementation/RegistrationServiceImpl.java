package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.service.contract.AutoLoginService;
import com.example.miroslavfacebookproject.service.contract.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class RegistrationServiceImpl extends BaseController implements RegistrationService {
    private final AutoLoginService autoLoginService;
    private final UserServiceImpl userServiceImpl;

    public RegistrationServiceImpl(AutoLoginService autoLoginService, UserServiceImpl userServiceImpl) {
        this.autoLoginService = autoLoginService;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public ModelAndView registration(RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", registerDTO);
            return redirect("register", "user", registerDTO);
        }
        userServiceImpl.registration(registerDTO);
        autoLoginService.autoLogin(registerDTO);
        return redirect("profile");

    }
}
