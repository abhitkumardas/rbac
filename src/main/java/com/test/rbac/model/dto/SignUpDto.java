package com.test.rbac.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
