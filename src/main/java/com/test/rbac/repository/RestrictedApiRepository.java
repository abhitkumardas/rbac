package com.test.rbac.repository;

import com.test.rbac.model.RestrictedApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestrictedApiRepository extends JpaRepository<RestrictedApi, Long> {
    List<RestrictedApi> findByUri(String uri);
    List<RestrictedApi> findByEmail(String email);
    boolean existsByUri(String uri);
    boolean existsByUriAndEmail(String uri, String email);
}
