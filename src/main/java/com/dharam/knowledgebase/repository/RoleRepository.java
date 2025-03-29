package com.dharam.knowledgebase.repository;

import com.dharam.knowledgebase.model.Role;
import com.dharam.knowledgebase.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}