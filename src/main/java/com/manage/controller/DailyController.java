package com.manage.controller;

import com.manage.entity.Daily;
import com.manage.entity.Result;
import com.manage.repository.DailyRepository;
import com.manage.service.DailyService;
import com.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/daily")
public class DailyController {

	@Resource
	DailyRepository dailyRepository;
	@Autowired
	DailyService dailyService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findDailys(Integer page, Integer size, String start, String end, Daily daily) {
		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<Daily> dailies = dailyService.findDailys(page - 1, size, start, end, daily);
			result.setCode(Constants.SUCCESS);
			result.setData(dailies.getContent());
			result.setTotal(dailies.getSize());
			result.setPages(dailies.getTotalPages());
			result.setIndex(page);
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
	public Result saveDaily(Daily daily) {
		Result result = new Result();
		try {
			dailyRepository.save(daily);
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
	public Result updateDaily(Daily daily) {
		Result result = new Result();
		Daily dailyEntity = dailyRepository.findOne(Long.getLong(daily.getId() + ""));
		if (dailyEntity != null) {
			try {
				dailyRepository.save(daily);
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
	public Result removeDaily(Integer id) {
		Result result = new Result();
		try {
			dailyRepository.delete(Long.getLong(id + ""));
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}
