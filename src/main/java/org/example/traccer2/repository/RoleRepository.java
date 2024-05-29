package org.example.traccer2.repository;

import org.example.traccer2.entity.Role;
import org.example.traccer2.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
