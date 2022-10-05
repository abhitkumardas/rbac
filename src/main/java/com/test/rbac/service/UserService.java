package com.test.rbac.service;

import com.test.rbac.config.JwtTokenUtil;
import com.test.rbac.model.Role;
import com.test.rbac.model.User;
import com.test.rbac.model.dto.SignUpDto;
import com.test.rbac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.test.rbac.config.AppConstants.ACC_CREATION_SUCCESSFUL;

@Service
public class UserService {

    @Autowired
    private ProjectionFactory projectionFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String signUp(SignUpDto signUpDto) {

        if (!StringUtils.hasLength(signUpDto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is mandatory information");
        }

        if (!StringUtils.hasLength(signUpDto.getEmail()) || !StringUtils.hasLength(signUpDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a valid email and password");
        }

        if (!StringUtils.hasLength(signUpDto.getRole())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role is mandatory field and can't be null");
        }

        Role role = roleService.findByName(signUpDto.getRole());

        if (Objects.isNull(role)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide valid Role");
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signUpDto.getPassword()));
        user.setRoles(Collections.singleton(role));
        user.setCreatedAt(new Date());
        user = userRepository.save(user);

        return ACC_CREATION_SUCCESSFUL;
    }

    public String signin(String email, String password) {

        if (!StringUtils.hasLength(email) || !StringUtils.hasLength(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a valid email and password");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return jwtTokenUtil.generateToken(authentication);
        } catch (Exception be) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid provided credentials");
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
