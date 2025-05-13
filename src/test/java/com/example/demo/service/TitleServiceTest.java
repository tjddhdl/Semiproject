package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.TitleDTO;
import com.example.demo.entity.enumfolder.Category;

@SpringBootTest
public class TitleServiceTest {

	@Autowired
	TitleService service;

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
