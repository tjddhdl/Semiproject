package com.example.demo.cart.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;
import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.service.TitleService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService service;
	
	@Autowired
	MemberService memberService;

	@Autowired
	TitleService titleService;
	
	// 장바구니 추가
	@PostMapping("/cartAdd")
	@ResponseBody
	public ResponseEntity<String> cartAdd(@ModelAttribute CartDTO dto, Principal principal) {
		MemberDTO mDTO = memberService.lookUpId(principal.getName());
		dto.setMemberNo(mDTO.getMemberNo());
		service.cartAdd(dto);
		return ResponseEntity.ok("성공");
		
	}
	
	// 장바구니 삭제
	asdf
	
	// 회원 장바구니 조회
	@GetMapping("/listLookUp")
	public void listLookUpAdmin(Principal principal, Model model) {
		MemberDTO mdto = memberService.lookUpId(principal.getName());
		List<CartDTO> list = service.listLookUp(mdto.getMemberNo());
		
		List<Map<String, Object>> cartTitleList = new ArrayList<>();
		for(CartDTO dto : list) {
			TitleDTO titleDTO = titleService.lookUp(dto.getTNo());
			Map<String, Object> map = new HashMap<>();
			map.put("cart", dto);
			map.put("title", titleDTO);
			cartTitleList.add(map);
		}
		model.addAttribute("list", cartTitleList);
	}
	
	// 로그인한 유저 정보로 조회하게 수정 필요
	// 관리자용 회원 장바구니 조회
	@GetMapping("/listLookUpAdmin")
	public void listLookUpAdmin(@RequestParam("no") int no, Model model) {
		List<CartDTO> list = service.listLookUp(no);
		model.addAttribute("list", list);
	}

	// 장바구니 수량수정
	@PostMapping("/list/modify")
	public void modify() {

	}

	// 선택한 장바구니 결제로 전달
}
