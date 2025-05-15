package com.example.demo.title.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.entity.Category;
import com.example.demo.title.entity.Model;
import com.example.demo.title.service.TitleService;

@SpringBootTest
public class TitleServiceTest {

	@Autowired
	TitleService service;

	@Test
	void 타이틀등록() {
		TitleDTO dto = TitleDTO.builder().titleName("오브라 딘 호의 귀환").price(11000).model(Model.PS4).stock(100)
				.image("main/title/img.img").ageRate(12).category(Category.ADVENTURE).build();
		service.register(dto);
	}

	@Test
	void 타이틀조회() {
		TitleDTO dto = service.lookup(6);
		System.out.println(dto);
	}

	@Test
	void 타이틀수정() {
		TitleDTO dto = service.lookup(6);
		LocalDate date = LocalDate.of(2024, 6, 22);
		dto.setReleaseDate(date);
		service.modify(dto);
	}

	@Test
	void 타이틀삭제() {
		service.delete(6);
	}

	@Test
	void 타이틀이름검색() {
		List<TitleDTO> list = service.search("가");
		for (TitleDTO dto : list) {
			System.out.println("목록: " + dto);
		}
	}

	@Test
	void 타이틀필터링() {
		List<TitleDTO> list = service.filter(Model.PS4, Category.ACTION, null, 20);
		for (TitleDTO dto : list) {
			System.out.println("목록: " + dto);
		}
	}
}
