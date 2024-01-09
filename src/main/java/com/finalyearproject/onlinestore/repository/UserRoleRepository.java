package com.mitron.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitron.onlinestore.domain.entities.Role;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, String> {

    Role findByAuthority(String authority);
}
