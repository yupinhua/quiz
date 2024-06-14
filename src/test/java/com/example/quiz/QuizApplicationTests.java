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
		//�p�� A, B, C, D, E �U�X�{�X��
		Map<String, Integer> map = new HashMap<>();
		for(String item : list) {
			String newStr = str.replace(item, "");//����Q�p�⪺�r�Ű����N
			int count = str.length() - newStr.length();//���r��P�s�r��۴�A�N�ા�D�Ӽ�
			map.put(item, count);
		}
		System.out.println(map);
	}
	
	@Test
	public void test4() {
		String str = "AABBBCDDAEEEAACCDD";
		//�p�� A, B, C, D, E �U�X�{�X��
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
