package com.test.rbac.service;

import com.test.rbac.model.Role;
import com.test.rbac.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private ProjectionFactory projectionFactory;

    public Role createRole() {
        //client can create roles
        return null;
    }

    public List<Role> saveAll(List<Role> roles) {

        //check if role already exists and add only new roles
        List<Role> rolesTobeCreated = roles.stream().filter(r -> StringUtils.hasLength(r.getName()) && Objects.isNull(roleRepository.findByName(r.getName()))).collect(Collectors.toList());
        return roleRepository.saveAll(rolesTobeCreated);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
