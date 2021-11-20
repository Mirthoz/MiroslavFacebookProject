package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.repository.RoleRepository;
import com.example.miroslavfacebookproject.entity.Role;
import com.example.miroslavfacebookproject.service.contract.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role takeUserRole(){
    Role userRole = roleRepository.findFirstByAuthority("ROLE_USER")
            .orElseThrow(() -> new IllegalStateException("User role not found"));
    return userRole;
    }
}
