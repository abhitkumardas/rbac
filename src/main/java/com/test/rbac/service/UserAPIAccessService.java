package com.test.rbac.service;


public interface UserAPIAccessService {
    public boolean isRestrictedAPI(String apiEndPoint);
    public boolean isUserCanAccess(String email, String apiEndPoint);
}
