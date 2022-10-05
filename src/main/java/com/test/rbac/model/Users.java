package com.test.rbac.model;

import java.util.Date;

public interface Users {
    Long getId();

    String getLastName();

    String getFirstName();

    String getEmail();

    Date getCreatedAt();
}
