package com.test.rbac.service;

import com.test.rbac.model.RestrictedApi;
import com.test.rbac.model.User;
import com.test.rbac.model.dto.RestrictedApiReq;
import com.test.rbac.repository.RestrictedApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Date;

@Service
public class RestrictedApiService {

    @Autowired
    private RestrictedApiRepository restrictedApiRepository;

    @Autowired
    private UserService userService;

    public Page<RestrictedApi> fetchAllRestrictedApis(Pageable pageable) {
        return restrictedApiRepository.findAll(pageable);
    }

    public RestrictedApi create(RestrictedApiReq restrictedApiReq, Principal principal) {
        User createdBy = userService.findUserByEmail(principal.getName()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "You don't have permission to use this Resource"));

        if (!StringUtils.hasLength(restrictedApiReq.getUri())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide valid URI");
        }
        if (!StringUtils.hasLength(restrictedApiReq.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide valid EMAIL");
        }
        if (!userService.userExistsByEmail(restrictedApiReq.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided email doesn't exists, please provide valid EMAIL");
        }

        return create(restrictedApiReq, createdBy);
    }

    private RestrictedApi create(RestrictedApiReq restrictedApiReq, User createdBy) {

        RestrictedApi restrictedApi = new RestrictedApi();
        restrictedApi.setCreatedBy(createdBy);
        restrictedApi.setCreatedDate(new Date());
        restrictedApi.setUri(restrictedApiReq.getUri());
        restrictedApi.setEmail(restrictedApiReq.getEmail());

        return restrictedApiRepository.save(restrictedApi);
    }

    public boolean isRestrictedURI(String uri) {
        return restrictedApiRepository.existsByUri(uri);
    }

    public boolean isAccessible(String uri, String email) {
        return restrictedApiRepository.existsByUriAndEmail(uri, email);
    }
}
