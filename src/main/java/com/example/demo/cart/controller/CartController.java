package com.example.demo.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService service;

	// 장바구니 추가

	// 장바구니 삭제

	// 로그인한 유저 정보로 조회하게 수정 필요
	// 회원 장바구니 조회
	@GetMapping("/listLookUp")
	public void listLookUp(@RequestParam("no") int no, Model model) {
		List<CartDTO> list = service.listLookUp(no);
		model.addAttribute("list", list);
	}

	// 장바구니 수량수정
	@PostMapping("/list/modify")
	public void modify() {

	}

	// 선택한 장바구니 결제로 전달
}
