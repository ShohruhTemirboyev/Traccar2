package org.example.traccer2.repository;

import org.example.traccer2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
    boolean existsByUserName(String userName);
}
