package com.example.quiz.vo;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrUpdateReq {

	private int id;
	
	private String name;// 名稱，例如"中午要喝啥",

	private String description;// 描述，例如"經查五"

	@JsonProperty("start_date") // 2024-06-01
	private LocalDate startDate;

	@JsonProperty("end_date") // 2024-06-30
	private LocalDate endDate;

	@JsonProperty("question_list") // 一張問卷很多問題，所以用list，就是範例那些金查五
	private List<Question> questionList;

	@JsonProperty("is_published")
	private boolean published;// 是否發佈

	public CreateOrUpdateReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateOrUpdateReq(String name, String description, LocalDate startDate, LocalDate endDate,
			List<Question> questionList, boolean published) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.questionList = questionList;
		this.published = published;
	}

	
	
	public CreateOrUpdateReq(int id, String name, String description, LocalDate startDate, LocalDate endDate,
			List<Question> questionList, boolean published) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.questionList = questionList;
		this.published = published;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public boolean isPublished() {
		return published;
	}

	public int getId() {
		return id;
	}

	
}
