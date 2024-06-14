package com.example.quiz.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fillin {
	
	//Question_id 
	@JsonProperty("question_id")
	private int qId;

	private String question;
	
	//多個選項是用分號(;)串接
	private String options;//選項
	
	//多個選項是用答案(;)串接
	private String answer;

	private String type;//問題類型 多選 單選
	
	private boolean necessary;//是否必填

	public Fillin() {
		super();
		
	}

	public Fillin(int qId, String question, String answer, String type, boolean necessary) {
		super();
		this.qId = qId;
		this.question = question;
		this.answer = answer;
		this.type = type;
		this.necessary = necessary;
	}


	public Fillin(int qId, String question, String options, String answer, String type, boolean necessary) {
		super();
		this.qId = qId;
		this.question = question;
		this.options = options;
		this.answer = answer;
		this.type = type;
		this.necessary = necessary;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNecessary() {
		return necessary;
	}

	public void setNecessary(boolean necessary) {
		this.necessary = necessary;
	}

	public String getOptions() {
		return options;
	}




	
	
}
