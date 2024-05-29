package org.example.traccer2.repository;

import org.example.traccer2.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    Message findByCode(Integer code);
}






