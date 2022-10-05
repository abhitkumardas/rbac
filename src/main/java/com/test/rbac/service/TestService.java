package com.test.rbac.service;

import com.test.rbac.model.Role;
import com.test.rbac.model.User;
import com.test.rbac.repository.RoleRepository;
import com.test.rbac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findUsers() {
        List<Role> roles = roleRepository.findAll();
        return userRepository.findAll();
    }
}
