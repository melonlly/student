package com.manage.repository;

import com.manage.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("scoreRepository")
public interface ScoreRepository extends JpaRepository<Score, Long>, JpaSpecificationExecutor<Score> {}
