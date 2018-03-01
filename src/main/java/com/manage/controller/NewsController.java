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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	NewsRepository newsRepository;
	@Autowired
	NewsService newsService;

	@RequestMapping("/list")
	@ResponseBody
	public Result findNews(@RequestBody Map<String, Object> req) {
		int page = Integer.parseInt(req.get("page").toString());
		int size = Integer.parseInt(req.get("size").toString());
		String start = req.get("start").toString();
		String end = req.get("end").toString();
		News news = new News();
		news.setName(req.get("name").toString());
		news.setPublisher(req.get("publisher").toString());
		news.setUpdater(req.get("updater").toString());

		System.out.println(page + " " + size + " " + start + " " + end);
		System.out.println(news.toString());

		Result result = new Result();
		try {
			// 分页默认从0页开始
			Page<News> newss = newsService.findNews(page - 1, size, start, end, news);
			result.setCode(Constants.SUCCESS);
			result.setData(newss.getContent());
			result.setTotal((int)newss.getTotalElements());
			result.setPages(newss.getTotalPages());
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
		News newEntity = newsRepository.findOne((long)news.getId());
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
			newsRepository.delete((long)id);
			result.setCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}
}
