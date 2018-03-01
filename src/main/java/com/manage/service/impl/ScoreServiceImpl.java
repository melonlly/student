package com.manage.service.impl;

import com.manage.entity.Daily;
import com.manage.entity.Score;
import com.manage.repository.DailyRepository;
import com.manage.repository.ScoreRepository;
import com.manage.service.DailyService;
import com.manage.service.ScoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service(value="scoreService")
public class ScoreServiceImpl implements ScoreService {

	@Resource
	ScoreRepository scoreRepository;

	@Override
	public Page<Score> findScores(Integer page, Integer size, Score score){
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<Score> scores = scoreRepository.findAll(new Specification<Score>(){
			@Override
			public Predicate toPredicate(Root<Score> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if(score != null){
					if(score.getName() != null && score.getName() != ""){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("name").as(String.class), score.getName())));
					}
					if(score.getExam() != null){
						criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("exam").as(Integer.class), score.getExam())));
					}
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return scores;
	}

}
