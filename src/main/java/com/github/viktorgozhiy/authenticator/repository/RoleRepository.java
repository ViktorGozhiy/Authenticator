package com.github.viktorgozhiy.authenticator.repository;

import com.github.viktorgozhiy.authenticator.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByAuthority(String authority);
}
