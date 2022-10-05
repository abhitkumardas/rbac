package com.test.rbac.controller;

import com.test.rbac.model.dto.RestrictedApiReq;
import com.test.rbac.service.RestrictedApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/admin/restricted-apis")
public class APIAccessManagementController {

    @Autowired
    private RestrictedApiService restrictedApiService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity fetchAllRestrictedApis(Pageable pageable) {
        return ResponseEntity.ok(restrictedApiService.fetchAllRestrictedApis(pageable));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity addRestrictedApi(@RequestBody RestrictedApiReq restrictedApiReq, Principal principal) {
        return ResponseEntity.ok(restrictedApiService.create(restrictedApiReq, principal));
    }
}
