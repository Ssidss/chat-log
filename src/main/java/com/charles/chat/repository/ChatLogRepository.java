package com.charles.chat.repository;

import com.charles.chat.model.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {

    List<ChatLog> findByJoinUserOrderById(String joinUser);

}
