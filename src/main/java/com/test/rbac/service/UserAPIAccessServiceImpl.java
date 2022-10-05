package com.test.rbac.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserAPIAccessServiceImpl implements UserAPIAccessService{

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RestrictedApiService restrictedApiService;

    @Override
    @Cacheable(cacheNames = "uriCache", key = "#uri")
    public boolean isRestrictedAPI(String uri) {
        //TODO: check if this comes under restricted apis
        return restrictedApiService.isRestrictedURI(uri);
    }

    @Override
    public boolean isUserCanAccess(String email, String uri) {
        //TODO: If Restricted then check if user has respective access
        return restrictedApiService.isAccessible(uri, email);
    }
}
