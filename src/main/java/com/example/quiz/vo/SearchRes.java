package com.example.quiz.vo;

import java.util.List;

import com.example.quiz.entity.Quiz;

public class SearchRes extends BasicRes {
	
	private List<Quiz> quizList;

	public SearchRes() {
		super();
	}

	public SearchRes(int statusCode, String message, List<Quiz> quizList) {//3屬性建構方法
		super(statusCode, message);//呼叫父類別的建構方法
		this.quizList = quizList;//自己的屬性建構方法
	}

	public List<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}

	
	
}
