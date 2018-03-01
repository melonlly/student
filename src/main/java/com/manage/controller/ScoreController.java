package com.manage.controller;

import com.manage.entity.Result;
import com.manage.entity.Score;
import com.manage.repository.ScoreRepository;
import com.manage.service.ScoreService;
import com.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/score")
public class ScoreController {

	@Resource
	ScoreRepository scoreRepository;
	@Autowired
	ScoreService scoreService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findScores(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		String exam = req.get("exam").toString();
		Score score = new Score();
		score.setName(req.get("name").toString());
		if(exam != null && exam != ""){
			score.setExam(Integer.parseInt(exam));
		}

		System.out.println(page + " " + size);
		System.out.println(score.toString());

		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<Score> scores = scoreService.findScores(page - 1, size, score);
			result.setCode(Constants.SUCCESS);
			result.setData(scores.getContent());
			result.setTotal((int)scores.getTotalElements());
			result.setPages(scores.getTotalPages());
			result.setPage(page);
			result.setSize(size);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

	@Modifying
	@RequestMapping("/add")
	@ResponseBody
	public Result saveScore(Score score) {
		Result result = new Result();
		try {
			scoreRepository.save(score);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

	@Modifying
	@RequestMapping("/update")
	@ResponseBody
	public Result updateScore(Score score) {
		Result result = new Result();
		Score scoreEntity = scoreRepository.findOne((long)score.getId());
		if (scoreEntity != null) {
			try {
				scoreRepository.save(score);
				result.setCode(Constants.SUCCESS);
			} catch (Exception e) {
				result.setCode(Constants.FAIL);
				result.setError(e.getMessage());
			}
		}
		return result;
	}

	@Modifying
	@RequestMapping("/remove")
	@ResponseBody
	public Result removeScore(Integer id) {
		Result result = new Result();
		try {
			scoreRepository.delete((long)id);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}
