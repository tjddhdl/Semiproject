package com.example.demo.title.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.entity.Category;
import com.example.demo.title.entity.ModelName;
import com.example.demo.title.service.TitleService;

@Controller
@RequestMapping("/title")
public class TitleController {

	@Autowired
	TitleService service;

	// 전체조회
	@GetMapping("/main")
	public void main(Model model) {
		List<TitleDTO> list = service.main();
		model.addAttribute("list", list);
	}

	// 이름 검색
	@GetMapping("/search")
	public void search(@RequestParam(name = "titleName") String titleName, Model model) {
		List<TitleDTO> list = service.search(titleName);
		model.addAttribute("list", list);
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
		System.out.println(list);
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
}
