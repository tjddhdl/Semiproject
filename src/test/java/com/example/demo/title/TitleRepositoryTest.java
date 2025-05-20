package com.example.demo.title;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.title.entity.Category;
import com.example.demo.title.entity.ModelName;
import com.example.demo.title.entity.Title;
import com.example.demo.title.repository.TitleRepository;

@SpringBootTest
public class TitleRepositoryTest {

	@Autowired
	TitleRepository repository;

	@Test
	void 타이틀추가() {
		LocalDate date = LocalDate.now();
		Title title = Title.builder().titleName("13기병방위권").price(1000).model(ModelName.PS4).releaseDate(date).stock(10)
				.ageRate(18).category(Category.ADVENTURE).build();
		repository.save(title);
	}

	@Test
	void 타이틀검색() {
		List<Title> list = repository.findByTitleNameContaining("1");
		System.out.println("목록: " + list);
	}

	@Test
	void 타이틀필터링검색() {
		List<Title> list = repository.findTitleByFilter(null, Category.ADVENTURE.toString(), null, null);
		System.out.println(Category.ACTION);
		for (Title title : list) {
			System.out.println("목록: " + title);
		}
	}

	@Test
	void 타이틀수정() {
		Optional<Title> optional = repository.findById(5);
		if (optional.isPresent()) {
			Title title = optional.get();
			title.setAgeRate(15);
			title.setImage("sdafsduf@#$$%fgjkvcxjsd");
			title.setPrice(3000);
			repository.save(title);
		}
	}

	@Test
	void 타이틀삭제() {
		repository.deleteById(5);
	}
}