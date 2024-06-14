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
	private QuizService quizService;//QuizService是一個介面，所以要用@Autowired來注入，quizService是一個實作該介面的類別
	@Autowired
	private QuizDao quizDao;
	
	@Test //單元測試
	public void createTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "健康餐?", "松阪豬;炸豬排;煎魚;烤雞腿",//
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(2, "丹丹", "1號餐;2號餐;3號餐;4號餐", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(3, "炒飯", "豬肉炒飯;海鮮炒飯;干貝馬鈴薯(推);綜合炒飯", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		CreateOrUpdateReq req = new CreateOrUpdateReq("午餐吃啥?","午餐吃啥?",LocalDate.of(2024, 6, 1),//不能建立當天日期，要不然會是錯，所以選6/1
				LocalDate.of(2024, 6, 1),questionList,true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getStatusCode() == 200, "create test false!!");//檢查結果是否正確，
		//去嘗試跑多次方法createtest()，來新增資料庫的資料，驗證是否新增成功
		
		//刪除測試資料 TODO
		
	}
	
	@Test //單元測試
	public void createNameErrorTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "健康餐?", "松阪豬;炸豬排;煎魚;烤雞腿",//
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(2, "丹丹", "1號餐;2號餐;3號餐;4號餐", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(3, "炒飯", "豬肉炒飯;海鮮炒飯;干貝馬鈴薯(推);綜合炒飯", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		CreateOrUpdateReq req = new CreateOrUpdateReq("","午餐吃啥?",LocalDate.of(2024, 6, 1),//
				LocalDate.of(2024, 6, 1),questionList,true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param name error!!"), "create test false!!");//檢查結果是否正確	
		
	}
	
	@Test //單元測試
	public void createStartDateErrorTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "健康餐?", "松阪豬;炸豬排;煎魚;烤雞腿",//
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(2, "丹丹", "1號餐;2號餐;3號餐;4號餐", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		questionList.add(new Question(3, "炒飯", "豬肉炒飯;海鮮炒飯;干貝馬鈴薯(推);綜合炒飯", //
				OptionType.SINGLE_CHOICE.getType() ,true));
		//今天是 2024/5/30 ，所以開始日期不能是當天或之前
		CreateOrUpdateReq req = new CreateOrUpdateReq("午餐吃啥?","午餐吃啥?",LocalDate.of(2024, 5, 30),//不能建立當天日期，要不然會是錯
				LocalDate.of(2024, 6, 1),questionList,true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param start date error!!"), "create test false!!");//檢查結果是否正確	
		
	}
	
	
	
	@Test //單元測試整理版，全部寫在一起
	public void createTest1() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "健康餐?", "松阪豬;炸豬排;煎魚;烤雞腿",//
				OptionType.SINGLE_CHOICE.getType() ,true));
		
		//測試 name error
		CreateOrUpdateReq req = new CreateOrUpdateReq("","午餐吃啥?",LocalDate.of(2024, 6, 1),//
				LocalDate.of(2024, 6, 1),questionList,true);
		BasicRes res = quizService.createOrUpdate(req);//呼叫create方法
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param name error!!"), "create test false!!");
		//getMessage()回復的字串，用equalsIgnoreCase方法 去比較("Param name error!!")，
		//檢查回復的結果是否一樣
		//equalsIgnoreCase()忽略大小寫
		
		
		//測試 start date error //今天是 2024/5/30 ，所以開始日期不能是當天或之前
		req = new CreateOrUpdateReq("午餐吃啥?","午餐吃啥?",LocalDate.of(2024, 5, 30),//故意用錯的request
				LocalDate.of(2024, 6, 1),questionList,true);
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param start date error!!"), "create test false!!");
		
		//測試 end date error :結束日期不能比開始日期早
		req = new CreateOrUpdateReq("午餐吃啥?","午餐吃啥?",LocalDate.of(2024, 6, 30),//
				LocalDate.of(2024, 6, 1),questionList,true);//equalsIgnoreCase忽略大小寫
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param end date error!!"), "create test false!!");
		
		//剩餘的邏輯全部判斷完之後，最後才會是測試成功的情境
		req = new CreateOrUpdateReq("午餐吃啥?","午餐吃啥?",LocalDate.of(2024, 6, 1),//
				LocalDate.of(2024, 6, 1),questionList,true);
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getStatusCode() == 200, "create test false!!");
		
	}
	
	
	
	
}
