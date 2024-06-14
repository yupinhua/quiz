package com.example.quiz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.quiz.constants.OptionType;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.BasicRes;
import com.example.quiz.vo.CreateOrUpdateReq;
import com.example.quiz.vo.Question;

@SpringBootTest
public class QuizServiceTests {

	@Autowired
	private QuizService quizService;//QuizService�O�@�Ӥ����A�ҥH�n��@Autowired�Ӫ`�J�AquizService�O�@�ӹ�@�Ӥ��������O
	@Autowired
	private QuizDao quizDao;
	
	@Test //�椸����
	public void createTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "���d�\?", "�Q����;���ޱ�;�γ�;�N���L",//
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(2, "����", "1���\;2���\;3���\;4���\", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(3, "����", "�ަת���;���A����;�z�����a��(��);��X����", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		CreateOrUpdateReq req = new CreateOrUpdateReq("���\�Yԣ?","���\�Yԣ?",LocalDate.of(2024, 6, 1),//����إ߷�Ѥ���A�n���M�|�O���A�ҥH��6/1
				LocalDate.of(2024, 6, 1),questionList,true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getStatusCode() == 200, "create test false!!");//�ˬd���G�O�_���T�A
		//�h���ն]�h����kcreatetest()�A�ӷs�W��Ʈw����ơA���ҬO�_�s�W���\
		
		//�R�����ո�� TODO
		
	}
	
	@Test //�椸����
	public void createNameErrorTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "���d�\?", "�Q����;���ޱ�;�γ�;�N���L",//
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(2, "����", "1���\;2���\;3���\;4���\", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(3, "����", "�ަת���;���A����;�z�����a��(��);��X����", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		CreateOrUpdateReq req = new CreateOrUpdateReq("","���\�Yԣ?",LocalDate.of(2024, 6, 1),//
				LocalDate.of(2024, 6, 1),questionList,true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param name error!!"), "create test false!!");//�ˬd���G�O�_���T	
		
	}
	
	@Test //�椸����
	public void createStartDateErrorTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "���d�\?", "�Q����;���ޱ�;�γ�;�N���L",//
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(2, "����", "1���\;2���\;3���\;4���\", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(3, "����", "�ަת���;���A����;�z�����a��(��);��X����", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		//���ѬO 2024/5/30 �A�ҥH�}�l�������O��ѩΤ��e
		CreateOrUpdateReq req = new CreateOrUpdateReq("���\�Yԣ?","���\�Yԣ?",LocalDate.of(2024, 5, 30),//����إ߷�Ѥ���A�n���M�|�O��
				LocalDate.of(2024, 6, 1),questionList,true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param start date error!!"), "create test false!!");//�ˬd���G�O�_���T	
		
	}
	
	
	
	@Test //�椸���վ�z���A�����g�b�@�_
	public void createTest1() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "���d�\?", "�Q����;���ޱ�;�γ�;�N���L",//
				OptionType.SINGLE_CHOICE.getType() ,true));
		
		//���� name error
		CreateOrUpdateReq req = new CreateOrUpdateReq("","���\�Yԣ?",LocalDate.of(2024, 6, 1),//
				LocalDate.of(2024, 6, 1),questionList,true);
		BasicRes res = quizService.createOrUpdate(req);//�I�screate��k
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param name error!!"), "create test false!!");
		//getMessage()�^�_���r��A��equalsIgnoreCase��k �h���("Param name error!!")�A
		//�ˬd�^�_�����G�O�_�@��
		//equalsIgnoreCase()�����j�p�g
		
		
		//���� start date error //���ѬO 2024/5/30 �A�ҥH�}�l�������O��ѩΤ��e
		req = new CreateOrUpdateReq("���\�Yԣ?","���\�Yԣ?",LocalDate.of(2024, 5, 30),//�G�N�ο���request
				LocalDate.of(2024, 6, 1),questionList,true);
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param start date error!!"), "create test false!!");
		
		//���� end date error :������������}�l�����
		req = new CreateOrUpdateReq("���\�Yԣ?","���\�Yԣ?",LocalDate.of(2024, 6, 30),//
				LocalDate.of(2024, 6, 1),questionList,true);//equalsIgnoreCase�����j�p�g
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param end date error!!"), "create test false!!");
		
		//�Ѿl���޿�����P�_������A�̫�~�|�O���զ��\������
		req = new CreateOrUpdateReq("���\�Yԣ?","���\�Yԣ?",LocalDate.of(2024, 6, 1),//
				LocalDate.of(2024, 6, 1),questionList,true);
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getStatusCode() == 200, "create test false!!");
		
	}
	
	
	
	
}
