package com.example.demo.title;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.title.Category;
import com.example.demo.title.TitleDTO;
import com.example.demo.title.TitleService;

@SpringBootTest
public class TitleServiceTest {

	@Autowired
	TitleService service;

	@Test
	void 타이틀등록() {
		TitleDTO dto = TitleDTO.builder().titleName("오브라 딘 호의 귀환").price(11000).model(Model.PS4).stock(null).image(null)
				.ageRate(null).category(null).build();
		service.register(null);
	}

	@Test
	void 타이틀조회() {

	}

	@Test
	void 타이틀수정() {

	}

	@Test
	void 타이틀삭제() {

	}

	@Test
	void 타이틀이름검색() {
		List<TitleDTO> list = service.search("졸려");
		for (TitleDTO dto : list) {
			System.out.println("목록: " + dto);
		}
	}

	@Test
	void 타이틀필터링() {
		List<TitleDTO> list = service.filter("PS5", Category.ACTION, null, 20);
		for (TitleDTO dto : list) {
			System.out.println("목록: " + dto);
		}
	}
}
