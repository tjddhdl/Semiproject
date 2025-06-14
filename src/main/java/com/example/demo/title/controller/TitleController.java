package com.example.demo.title.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.entity.Category;
import com.example.demo.title.entity.ModelName;
import com.example.demo.title.entity.QTitle;
import com.example.demo.title.service.TitleService;
import com.querydsl.core.BooleanBuilder;

@Controller
@RequestMapping("/title")
public class TitleController {

	@Autowired
	TitleService service;

	// 전체조회
	@GetMapping("/main")
	public void main(Model model) {
		List<TitleDTO> list = service.main();
		list.sort(Comparator.comparing(TitleDTO::getReleaseDate).reversed());
		model.addAttribute("list", list);
	}

	// 이름 검색
//	@GetMapping("/search")
//	public void search(@RequestParam(name = "titleName") String titleName, Model model) {
//		List<TitleDTO> list = service.search(titleName);
//		model.addAttribute("list", list);
//	}

	// 검색
	@GetMapping("/search")
	public void search(Model model) {
		List<TitleDTO> list = service.main();
		model.addAttribute("modelNames", ModelName.values());
		model.addAttribute("categoryes", Category.values());
	}

	@PostMapping("/search")
	@ResponseBody
	public List<TitleDTO> searchPost(@RequestParam(name = "titleName", required = false) String titleName,
			@RequestParam(name = "model", required = false) ModelName model,
			@RequestParam(name = "category", required = false) Category category,
			@RequestParam(name = "ageRating", required = false) List<Integer> ageList,
			@RequestParam(name = "price", required = false) List<String> priceList,
			@RequestParam(name = "date", required = false) List<LocalDate> dateList,
			@RequestParam(name = "stock", required = false) Integer stock) {
		List<TitleDTO> list = service.search(titleName, model, category, ageList, priceList, dateList, stock);
		return list;
	}

	// 타이틀 필터링 검색
	@GetMapping("filter")
	public void filter(@RequestParam(name = "modelName", required = false) String modelName,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "minAge", required = false) Integer minAge,
			@RequestParam(name = "maxAge", required = false) Integer maxAge, Model model) {

		if (modelName == "")
			modelName = null;
		if (category == "")
			category = null;

		List<TitleDTO> list = service.filter(modelName, category, minAge, maxAge);
		model.addAttribute("list", list);
	}

	// 타이틀조회
	@GetMapping("/lookUp")
	public void lookUp(@RequestParam(name = "no") int no, Model model) {
		TitleDTO dto = service.lookUp(no);
		model.addAttribute("dto", dto);
	}

	// 관리자만 사용할 수 있게 작업 필요
	// 타이틀수정페이지
	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		TitleDTO dto = service.lookUp(no);
		model.addAttribute("dto", dto);
		model.addAttribute("modelNames", ModelName.values());
		model.addAttribute("categories", Category.values());
	}

	// 관리자만 사용할 수 있게 작업 필요
	// 타이틀수정 적용
	@PostMapping("/modify")
	public String modifyPost(TitleDTO dto, RedirectAttributes redirectAttributes) {
		if (dto.getTitleName() == "")
			dto.setTitleName(null);
		if (dto.getImage() == "")
			dto.setImage(null);
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getTNo());
		return "redirect:/title/lookUp";
	}

	// 관리자만 사용할 수 있게 작업 필요
	// 타이틀 등록페이지
	@GetMapping("/register")
	public void register(Model model) {
		model.addAttribute("modelNames", ModelName.values());
		model.addAttribute("categories", Category.values());
	}

	// 관리자만 사용할 수 있게 작업 필요
	// 타이틀 등록 적용
	@PostMapping("/register")
	public String registerPost(TitleDTO dto) {
		service.register(dto);
		return "redirect:/title/main";
	}

	// 관리자만 사용할 수 있게 작업 필요
	// 상품 삭제
	@PostMapping("/delete")
	public String delete(TitleDTO dto) {
		service.delete(dto.getTNo());
		return "redirect:/title/main";
	}

}
