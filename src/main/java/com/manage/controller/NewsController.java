package com.manage.controller;

import com.manage.entity.News;
import com.manage.entity.Result;
import com.manage.repository.NewsRepository;
import com.manage.service.NewsService;
import com.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	NewsRepository newsRepository;
	@Autowired
	NewsService newsService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findNews(Integer page, Integer size, String start, String end, News news) {
		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<News> newss = newsService.findNews(page - 1, size, start, end, news);
			result.setCode(Constants.SUCCESS);
			result.setData(newss.getContent());
			result.setTotal(newss.getSize());
			result.setPages(newss.getTotalPages());
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
	public Result saveNews(News news) {
		Result result = new Result();
		try {
			newsRepository.save(news);
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
	public Result updateNews(News news) {
		Result result = new Result();
		News newEntity = newsRepository.findOne(Long.getLong(news.getId() + ""));
		if (newEntity != null) {
			try {
				newsRepository.save(news);
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
	public Result removeNews(Integer id) {
		Result result = new Result();
		try {
			newsRepository.delete(Long.getLong(id + ""));
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}
