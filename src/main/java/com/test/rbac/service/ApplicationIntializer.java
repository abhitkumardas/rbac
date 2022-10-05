package com.test.rbac.service;

import com.test.rbac.config.AppConstants;
import com.test.rbac.model.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationIntializer {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RoleService roleService;

    public void initialize() {
        logger.info("Application initialization started...");
        createRoles();
        createSuperAdmin();

        logger.info("Application started successfully...");
    }

    private void createRoles() {
        logger.info("Creating Roles...");
        List<Role> roles = Arrays.asList(
                new Role(AppConstants.ROLE_SUPERADMIN, new Date()),
                new Role(AppConstants.ROLE_ADMIN, new Date()),
                new Role(AppConstants.ROLE_USER, new Date())
        );
        roleService.saveAll(roles);
    }

    private void createSuperAdmin() {
    }
}
