package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Response;

@Repository
public interface ResponseDao extends JpaRepository<Response, Integer> {

	public boolean existsByQuizIdAndPhone(int quizId,String phone);
	//�n�P�_����@�ӤH���ƶ񵪡A�ҥH�a�Jint quizId,String phone�ӧP�_
	public List<Response> findByQuizId(int quizId);
}
