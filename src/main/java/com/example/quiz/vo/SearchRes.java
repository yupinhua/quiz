package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.entity.Quiz;

public class SearchRes extends BasicRes {
	
	private List<Quiz> quizList;

	public SearchRes() {
		super();
	}

	public SearchRes(int statusCode, String message, List<Quiz> quizList) {//3�ݩʫغc��k
		super(statusCode, message);//�I�s�����O���غc��k
		this.quizList = quizList;//�ۤv���ݩʫغc��k
	}

	public List<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}

	
	
}
