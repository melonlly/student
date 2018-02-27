package com.manage.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_score")
public class Score {

	@Id
	@GeneratedValue
	private Integer id;
	private String name; // 学生姓名
	private Integer exam; // 考试类型（1,2,3）
	private String week1; // 周测第一次
	private String week2; // 周测第二次
	private String week3; // 周测第三次
	private String english; // 英语
	private String social; // social
	private String science; // science
	private String chinese; // 中文
	private String math; // 数学
	private String physical; // 物理

	public Score() {
	}

	public Score(String name, Integer exam, String week1, String week2, String week3, String english, String social, String science, String chinese, String math, String physical) {
		this.name = name;
		this.exam = exam;
		this.week1 = week1;
		this.week2 = week2;
		this.week3 = week3;
		this.english = english;
		this.social = social;
		this.science = science;
		this.chinese = chinese;
		this.math = math;
		this.physical = physical;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExam() {
		return exam;
	}

	public void setExam(Integer exam) {
		this.exam = exam;
	}

	public String getWeek1() {
		return week1;
	}

	public void setWeek1(String week1) {
		this.week1 = week1;
	}

	public String getWeek2() {
		return week2;
	}

	public void setWeek2(String week2) {
		this.week2 = week2;
	}

	public String getWeek3() {
		return week3;
	}

	public void setWeek3(String week3) {
		this.week3 = week3;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public String getScience() {
		return science;
	}

	public void setScience(String science) {
		this.science = science;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public String getMath() {
		return math;
	}

	public void setMath(String math) {
		this.math = math;
	}

	public String getPhysical() {
		return physical;
	}

	public void setPhysical(String physical) {
		this.physical = physical;
	}
}
