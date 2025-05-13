package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Title;
import com.example.demo.entity.enumfolder.Category;

@SpringBootTest
public class TitleRepositoryTest {

	@Autowired
	TitleRepository repository;

	@Test
	void 타이틀추가() {
		LocalDate date = LocalDate.now();
		Title title = Title.builder().titleName("졸려").price(1000).model("PS4").releaseDate(date).stock(10).ageRate(18)
				.category(Category.ACTION).build();
		repository.save(title);
	}

	@Test
	void 타이틀검색() {
		List<Title> list = repository.findByTitleNameContaining("락");
		System.out.println("오램ㄴㅇ란ㅁ올: " + list);
	}

	@Test
	void 타이틀필터링검색() {
		List<Title> list = repository.findTitleByFilter("PS4", null, 0, 17);
		for (Title title : list) {
			System.out.println("목록: " + title);
		}
	}
}

// 장바구니에 있는 상품으로 주문 생성 장바구니 목록을 오더에 하나하나 추가한다
// 카트에 있는 목록을 하나만 넘기는 기능하고 전체를 넘기는 기능?
// 요즘은 체크박스로 결제하고 싶은 상품을 선택할 수 있잖아 그거 하려면 어떻게
// 하지
// 카트 단독 상품 결제하는 기능