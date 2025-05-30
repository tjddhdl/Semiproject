package com.example.demo.title.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.entity.Category;
import com.example.demo.title.entity.ModelName;
import com.example.demo.title.entity.QTitle;
import com.example.demo.title.entity.Title;
import com.example.demo.title.repository.TitleRepository;
import com.example.demo.title.util.FileUtility;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class TitleServiceImpl implements TitleService {

	@Autowired
	TitleRepository repository;

	@Autowired
	FileUtility utility;

	// 게임등록
	@Override
	public void register(TitleDTO dto) {
		dto.setImage(utility.fileUpload(dto.getFile()));
		Title title = dtoToEntity(dto);
		repository.save(title);
	}

	// 전체조회
	@Override
	public List<TitleDTO> main() {
		List<Title> list = repository.findTitleByFilter(null, null, null, null);
		List<TitleDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
	}

	// 게임정보조회
	@Override
	public TitleDTO lookUp(int no) {
		Optional<Title> optional = repository.findById(no);
		if (optional.isPresent()) {
			Title title = optional.get();
			TitleDTO dto = entityToDTO(title);
			return dto;
		}
		return null;
	}

	// 게임정보수정
	@Override
	public void modify(TitleDTO dto) {
		Optional<Title> optional = repository.findById(dto.getTNo());
		if (dto.getFile() != null) {
			dto.setImage(utility.fileUpload(dto.getFile()));
		}
		if (optional.isPresent()) {
			Title title = optional.get();
			if (dto.getTitleName() != null)
				title.setTitleName(dto.getTitleName());
			if (dto.getPrice() != null)
				title.setPrice(dto.getPrice());
			if (dto.getModel() != null)
				title.setModel(dto.getModel());
			if (dto.getReleaseDate() != null)
				title.setReleaseDate(dto.getReleaseDate());
			if (dto.getStock() != null)
				title.setStock(dto.getStock());
			if (dto.getImage() != null && dto.getImage() != title.getImage())
				title.setImage(dto.getImage());
			if (dto.getAgeRate() != null)
				title.setAgeRate(dto.getAgeRate());
			if (dto.getCategory() != null)
				title.setCategory(dto.getCategory());
			repository.save(title);
		}

	}

	// 게임삭제
	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}

	// 타이틀 이름으로 검색
	@Override
	public List<TitleDTO> searchName(String titleName) {
		List<Title> list = repository.findByTitleNameContaining(titleName);
		List<TitleDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
	}

	// 타이틀 필터링 옵션
	@Override
	public List<TitleDTO> filter(String model, String category, Integer minAge, Integer maxAge) {
		List<Title> list = repository.findTitleByFilter(model, category, minAge, maxAge);
		List<TitleDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
	}

	// 타이틀 검색
	@Override
	public List<TitleDTO> search(String titleName, ModelName model, Category category, List<Integer> ageList,
			List<String> priceList, List<LocalDate> dateList, Integer stock) {
		QTitle qTitle = QTitle.title;
		BooleanBuilder builder = new BooleanBuilder();
		if (!titleName.trim().isEmpty()) {
			BooleanExpression expression1 = qTitle.titleName.contains(titleName);
			builder.and(expression1);
		}
		if (model != null) {
			BooleanExpression expression2 = qTitle.model.eq(model);
			builder.and(expression2);
		}
		if (category != null) {
			BooleanExpression expression3 = qTitle.category.eq(category);
			builder.and(expression3);
		}
		if (ageList != null) {
			BooleanExpression expression4 = qTitle.ageRate.in(ageList);
			builder.and(expression4);
		}
		if (priceList != null) {
			for (String price : priceList) {
				String[] range = price.replaceAll("'", "").split("-");
				if (range[0].equals("50000")) {
					int from = Integer.parseInt(range[0]);
					BooleanExpression expression5 = qTitle.price.goe(from);
					builder.and(expression5);
				} else {
					int from = Integer.parseInt(range[0]);
					int to = Integer.parseInt(range[1]);
					BooleanExpression expression5 = qTitle.price.between(from, to);
					builder.and(expression5);
				}
			}
		}
		// dateList는 3번에 걸쳐 나눠야함
		if (dateList.get(0) != null && dateList.get(1) != null) {
			BooleanExpression expression6 = qTitle.releaseDate.between(dateList.get(0), dateList.get(1));
			builder.and(expression6);
		} else if (dateList.get(0) != null && dateList.get(1) == null) {
			BooleanExpression expression6 = qTitle.releaseDate.goe(dateList.get(0));
			builder.and(expression6);
		} else if (dateList.get(0) == null && dateList.get(1) != null) {
			BooleanExpression expression6 = qTitle.releaseDate.loe(dateList.get(1));
			builder.and(expression6);
		}
		if (stock != null) {
			BooleanExpression expression7 = qTitle.stock.ne(stock);
			builder.and(expression7);
		}
		Iterable<Title> iterable = repository.findAll(builder);
		List<TitleDTO> list = StreamSupport.stream(iterable.spliterator(), false).map(Title -> entityToDTO(Title))
				.collect(Collectors.toList());

		return list;
	}

}
