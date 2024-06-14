package com.example.quiz.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {//問題的類別
	
	private int id;

	private String title;//純茶類
	
	private String options;//選項，例如"紅茶;綠茶;烏龍;青茶",

	private String type;//問題類型，例如 "單選"、"多選"、"文字"
	@JsonProperty("is_necessary")
	private boolean necessary;//是否必填，例如 "是true"、"否false"

	public Question() {
		super();	
	}

	public Question(int id, String title, String options, String type, boolean necessary) {
		super();
		this.id = id;
		this.title = title;
		this.options = options;
		this.type = type;
		this.necessary = necessary;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void setOptions(String options) {
		this.options = options;
	}
	

}
