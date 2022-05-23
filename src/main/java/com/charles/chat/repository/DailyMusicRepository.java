package com.charles.chat.repository;

import com.charles.chat.model.DailyMusic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyMusicRepository extends JpaRepository<DailyMusic, Long> {
}
