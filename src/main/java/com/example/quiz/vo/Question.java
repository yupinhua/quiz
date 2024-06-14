package com.example.quiz.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {//���D�����O
	
	private int id;

	private String title;//�¯���
	
	private String options;//�ﶵ�A�Ҧp"����;���;�Q�s;�C��",

	private String type;//���D�����A�Ҧp "���"�B"�h��"�B"��r"
	@JsonProperty("is_necessary")
	private boolean necessary;//�O�_����A�Ҧp "�Otrue"�B"�_false"

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
