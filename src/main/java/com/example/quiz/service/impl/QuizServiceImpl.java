package com.example.quiz.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.quiz.constants.OptionType;
import com.example.quiz.constants.ResMessage;
import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.BasicRes;
import com.example.quiz.vo.CreateOrUpdateReq;
import com.example.quiz.vo.DeleteReq;
import com.example.quiz.vo.FillinReq;
import com.example.quiz.vo.Question;
import com.example.quiz.vo.SearchReq;
import com.example.quiz.vo.SearchRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizDao quizDao;
	
	@Override
	public BasicRes createOrUpdate(CreateOrUpdateReq req) {//create�O�D�y�{
		//�ˬd�Ѽ�
		BasicRes checkResult =  checkParams(req);
		//checkResult == null�� �A��ܰѼ��ˬd�����T
		if(checkResult != null) {
			return checkResult;//�p�G�o�{�����A�N�⵲�Greturn�^�h�A���X��Ӥ�k
		//=============�]�i�H�Υt�@�ؼg�k===============
		//if(checkResult.getStatusCode() != 200) {}
		// �o�˼g�A�̫᭱��return new BasicRes(ResMessage.SUCCESS.getCode(),
			//ResMessage.SUCCESS.getMessage());
		}
		//�]�� Quiz �� questions ����Ʈ榡�O String�A�ҥH�n�N req �� List<Question> �ন String
		//�z�LObjectMapper�i�H�⪫��(���O)�নJSON�榡���r��
		ObjectMapper mapper = new ObjectMapper();
		try {
			String questionStr = mapper.writeValueAsString(req.getQuestionList());
			
			
			//�Yreq ���� id >0�A��ܧ�s�w�s�b����� ;�Yid =0 ;�h��ܭn�s�W
			if(req.getId() > 0) { //>0�N��id����
				//�H�U��ؤ覡�ܤ@
				//�ϥΤ�k1�A�z�L findById �ӧP�_��ƬO�_�s�b�A�Y����ơA�N�|�^�Ǥ@�㵧�����(�i���ƶq�|���j)
				//�ϥΤ�k2�A�]���O�z�L existsById �ӧP�_��ƬO�_�s�b�A�ҥH�^�Ǫ���ƥû����u�|�O�@��bit(0��1)
				//��k 1. �z�L findById:�Y����ơA�^�Ǿ㵧���
//				 Optional<Quiz> op = quizDao.findById(req.getId());
//				 //�P�_�O�_�����
//				 if(op.isEmpty()) { //op.isEmpty()��ܨS���
//					 return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(),
//								ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
//				 }
//				 Quiz quiz = op.get();
//				 //�]�wreq�s��(�ȱq req ��)
//				 //�N req �����s�ȳ]�w���ª�quiz ���A���]�wid �A�]��id�@��
//				 quiz.setName(req.getName());
//				 quiz.setDescription(req.getDescription());
//				 quiz.setStartDate(req.getStartDate());
//				 quiz.setEndDate(req.getEndDate());
//				 quiz.setQuestions(questionStr);
//				 quiz.setPublished(req.isPublished());
				
				 //��k 2. �z�L existsById:�^�Ǥ@�� bit���ȡA�ˬdid�s���s�b?
				//�o��n�P�_�q req �a�i�Ӫ� id �O�_�u�����s�b��DB��
				//�]���Y id ���s�b�A�S���ˬd�A����{���X�b�I�s JPA �� save��k�ɡA�|�ܦ��s�W
				 boolean boo = quizDao.existsById(req.getId());
				 if(!boo) {//!boo ��ܸ�Ƥ��s�b
					 return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(),
								ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
							 
				 }
				 //�nnew�@�ӷs��Quiz�A�n��id��i�h
				//Quiz quiz = new Quiz(req.getId(),req.getName(), req.getDescription(), req.getStartDate(),
					//	req.getEndDate(), questionStr, req.isPublished());
				 //��s
				 //quizDao.save(quiz);	�o���i�H���μg�F�A�]��id�s�b�A�N�|��s
		
			}
			//===============================================================
			// �W�z�@��q if �{���X�i�H�Y��H�U�o�q
			//  if(req.getId() > 0 && !quizDao.existsById(req.getId())) {   
			//  	return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(), 
			//  		ResMessage.UPDATE_ID_NOT_FOUND.getMessage());   
			  // }
			//==================================================================
			//Quiz quiz = new Quiz(req.getName(), req.getDescription(), req.getStartDate(),
					//req.getEndDate(), questionStr, req.isPublished());
			//quizDao.save(quiz);
			// �]���ܼ� quiz �u�ϥΤ@���A�]���i�H�ϥΰΦW���O�覡���g(���ݭn�ܼƱ�)�A�]�i�H�ΤW������檺�覡
			
			//new Quiz() ���a�J req.getId()�OPK�A�b�I�ssave�ɡA�|���h�ˬd PK �O�_�s�b��DB���A
			//�Y�s�b --> ��s : �Y���s�b --> �s�W
			//req ���S�������ɡA�w�]�O0�A�]�� id����ƫ��A�Oint
			quizDao.save(new Quiz(req.getId(),req.getName(), req.getDescription(), req.getStartDate(),
					req.getEndDate(), questionStr, req.isPublished()));
		} catch (JsonProcessingException e) {
			return new BasicRes(ResMessage.JSON_PROCESSING_EXCEPTION.getCode(),
					ResMessage.JSON_PROCESSING_EXCEPTION.getMessage());
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
	}

	private BasicRes checkParams(CreateOrUpdateReq req) {
		// �ˬd�ݨ��Ѽ�
		// StringUtils.hasText(�r��):�|�ˬd�r��O�_��null . �Ŧr�� .���ťզr��A�Y�O�ŦX3�ب䤤�@���A�|�^false
		// �e���[����ĸ���ܤϦV���N��A�N��O�p�G�r�ꪺ�ˬd���G�O false���ܡA�N�|�i�� if����@�϶�
		// !StringUtils.hasText(req.getName())���P�� StringUtils.hasText(req.getName())==
		// false
		// ����ĸ��A�N��O: �_
		if (!StringUtils.hasText(req.getName())) {
			return new BasicRes(ResMessage.PARAM_QUIZ_NAME_ERROR.getCode(), ResMessage.PARAM_QUIZ_NAME_ERROR.getMessage());
		}
		if (!StringUtils.hasText(req.getDescription())) {
			return new BasicRes(ResMessage.PARAM_DESCRIPTION_ERROR.getCode(),
					ResMessage.PARAM_DESCRIPTION_ERROR.getMessage());
		}
		// 1. �}�l�ɶ�����p�󵥩��e�ɶ�
		// LocalDate.now(): ���o�t�η�e�ɶ�
		// req.getStartDate().isAfter(LocalDate.now()): �Y req �����}�l�ɶ����e�ɶ��ߡA�|�o�� true
		// !req.getStartDate().isAfter(LocalDate.now()): �e�����[��ĸ��A��ܷ|�o��ۤϪ����G�A�N�O�}�l�ɶ�
		//                                               �|����p���e�ɶ�
		if (req.getStartDate() == null || !req.getStartDate().isAfter(LocalDate.now())) {
			return new BasicRes(ResMessage.PARAM_START_DATE_ERROR.getCode(),
					ResMessage.PARAM_START_DATE_ERROR.getMessage());
		}
		//����1�q�P�_�i�H���μg�A�]���}�l�ɶ��w�g�P�_�F"�}�l�ɶ�">=��e�ɶ��A�ӫ᭱���P�_�����ɶ��O>="�}�l�ɶ�"
		//�{���X�������o��ɡA��ܶ}�l�ɶ��@�w>=��e�ɶ�
		//�ҥH���ݭn�P�_ !req.getEndDate().isAfter(LocalDate.now())
		//1.�����ɶ�����p�󵥩��e�ɶ� 2.�����ɶ�����p��}�l�ɶ�
		if (req.getEndDate() == null || req.getEndDate().isBefore(req.getStartDate())) {
			return new BasicRes(ResMessage.PARAM_END_DATE_ERROR.getCode(),
					ResMessage.PARAM_END_DATE_ERROR.getMessage());
		}
		// �ˬd���D�Ѽ�
		if (CollectionUtils.isEmpty(req.getQuestionList())) {
			return new BasicRes(ResMessage.PARAM_QUESTION_LIST_NOT_FOUND.getCode(),
					ResMessage.PARAM_QUESTION_LIST_NOT_FOUND.getMessage());
		}
		// �@�i�ݨ��i��|���h�Ӱ��D�A�ҥH�n�v���ˬd�C�@�D���Ѽ�
		for (Question item : req.getQuestionList()) {
			if (item.getId() <= 0) {
				return new BasicRes(ResMessage.PARAM_QUESTION_ID_ERROR.getCode(),
						ResMessage.PARAM_QUESTION_ID_ERROR.getMessage());
			}
			if (!StringUtils.hasText(item.getTitle())) {
				return new BasicRes(ResMessage.PARAM_TITLE_ERROR.getCode(),
						ResMessage.PARAM_TITLE_ERROR.getMessage());
			}
			
			if (!StringUtils.hasText(item.getType())) {
				return new BasicRes(ResMessage.PARAM_TYPE_ERROR.getCode(),
						ResMessage.PARAM_TYPE_ERROR.getMessage());
			}
			//�� optoin_type �O���Φh��ɡAoptions �N����O�Ŧr��
			//�� optoin_type �O��r�ɡAoptions ���\�O�Ŧr��
			//�H�U�����ˬd: ��optoin_type �O���Φh��ɡA�B options�O�Ŧr��A��^���~
			if(item.getType().equals(OptionType.SINGLE_CHOICE.getType())
				|| item.getType().equals(OptionType.MULTI_CHOICE.getType())){		
				if (!StringUtils.hasText(item.getOptions())) {
					return new BasicRes(ResMessage.PARAM_OPTIONS_ERROR.getCode(),
							ResMessage.PARAM_OPTIONS_ERROR.getMessage());
				}
			}
		}
		
		return null;//�]�i�HBasicRes�^�_200���\

	}

	@Override
	public SearchRes search(SearchReq req) {
		String name = req.getName();
		LocalDate start = req.getStartDate();
		LocalDate end = req.getEndDate();
		//���] name �Onull�άO���ťժ��r��A�i�H�����S����J����ȡA�N��ܭn���o����
		//�z�L JPA ��containing ����k�A����ȬO�Ŧr��ɡA�|�j�M����
		//�ҥH�n�� name ���ȬO null �Υ��ťզr��ɡA�ഫ���Ŧr��
		if(!StringUtils.hasText(name)) {
			name = " ";		
		}
		if(start == null) {
			start = LocalDate.of(1970, 1, 1);
		}
		if(end == null) {
			end = LocalDate.of(2999, 12, 31);
		}
		//List<Quiz> list = quizDao.findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(name,
		//start, end);
		//return new SearchRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage(),res);
				
		return new SearchRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage(),
				quizDao.findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(name,
						  start,end));
	}

	@Override	//�мg�A�l���O�~�Ӥ����O�ɡA�i�H��g�����O�즳����k���e
	public BasicRes delete(DeleteReq req) {
		// �Ѽ��ˬd
		if(!CollectionUtils.isEmpty(req.getIdList())) {
			//�R���ݨ�
			try {
				quizDao.deleteAllById(req.getIdList());
			}catch(Exception e) {
				//�� deleteAllById ��k���Aid ���Ȥ��s�b�ɡAJPA �|����
			    //�]���b�R�����e�AJPA �|���j�M�a�J�� id �ȡA�Y�S���G�N�|����
			    //�ѩ��ڤW�S�R�������ơA�]���N���ݭn��o�� Exception �@�B�z
			}
			
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
	}

	
	
}
