package com.manage.service;

import com.manage.entity.Score;
import org.springframework.data.domain.Page;

public interface ScoreService {

	// 查找新闻信息
	Page<Score> findScores(Integer page, Integer size, Score score);

}
