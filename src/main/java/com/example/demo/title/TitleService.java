package com.example.demo.title;

import java.util.List;

public interface TitleService {

	default Title dtoToEntity(TitleDTO dto) {
		Title title = Title.builder().titleName(dto.getTitleName()).price(dto.getPrice()).model(dto.getModel())
				.releaseDate(dto.getReleaseDate()).stock(0).image(dto.getImage()).ageRate(dto.getAgeRate())
				.category(dto.getCategory()).build();
		return title;
	}

	default TitleDTO entityToDTO(Title t) {
		TitleDTO dto = TitleDTO.builder().tNo(t.getTNo()).titleName(t.getTitleName()).price(t.getPrice())
				.model(t.getModel()).releaseDate(t.getReleaseDate()).stock(t.getStock()).image(t.getImage())
				.ageRate(t.getAgeRate()).category(t.getCategory()).build();
		return dto;
	}

	// 게임등록
	void register(TitleDTO dto);

	// 게임정보조회
	TitleDTO lookup(int no);

	// 게임정보수정
	void modify(TitleDTO dto);

	// 게임삭제
	void delete(int id);

	// 타이틀 이름으로 검색
	List<TitleDTO> search(String titleName);

	// 타이틀 필터링 옵션
	List<TitleDTO> filter(String model, Category category, Integer minAge, Integer maxAge);
}
