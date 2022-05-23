package com.charles.chat.repository.auth;

import com.charles.chat.model.auth.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
