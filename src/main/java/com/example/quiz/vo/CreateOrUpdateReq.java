package com.example.quiz.vo;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrUpdateReq {

	private int id;
	
	private String name;// �W�١A�Ҧp"���ȭn��ԣ",

	private String description;// �y�z�A�Ҧp"�g�d��"

	@JsonProperty("start_date") // 2024-06-01
	private LocalDate startDate;

	@JsonProperty("end_date") // 2024-06-30
	private LocalDate endDate;

	@JsonProperty("question_list") // �@�i�ݨ��ܦh���D�A�ҥH��list�A�N�O�d�Ҩ��Ǫ��d��
	private List<Question> questionList;

	@JsonProperty("is_published")
	private boolean published;// �O�_�o�G

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
