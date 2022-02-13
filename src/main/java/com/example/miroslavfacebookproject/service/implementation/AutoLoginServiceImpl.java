package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.service.contract.AutoLoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AutoLoginServiceImpl implements AutoLoginService {
    @Resource(name = "authenticationManager")
    private AuthenticationManager authManager;

    @Override
    public void autoLogin(RegisterDTO registerDTO) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(registerDTO.getEmail(), registerDTO.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
    }
}
