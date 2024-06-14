package com.example.quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuizApplicationTests {

	@Test
	public void test3() {
		List<String>list = List.of("A","B","C","D","E");
		String str ="AABBBCDDAEEEAACCDD";
		//計算 A, B, C, D, E 各出現幾次
		Map<String, Integer> map = new HashMap<>();
		for(String item : list) {
			String newStr = str.replace(item, "");//先把想計算的字符做取代
			int count = str.length() - newStr.length();//把原字串與新字串相減，就能知道個數
			map.put(item, count);
		}
		System.out.println(map);
	}
	
	@Test
	public void test4() {
		String str = "AABBBCDDAEEEAACCDD";
		//計算 A, B, C, D, E 各出現幾次
		int count = 0;
		int startIndex = 0;
		while(startIndex < str.length()) {
			int index = str.indexOf("A", startIndex);
			if(index == -1) {
				break;
			}
			count++;
			startIndex = index + 1;
		}
		System.out.println(count);
	}	
	
}
