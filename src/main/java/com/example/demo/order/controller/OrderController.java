package com.example.demo.order.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {

	// 주문 확인 페이지
	@PostMapping("/orderCheck")
	public String orderCheck(@RequestParam("cartIds") List<Integer> cartIds) {
		System.out.println("야이!!!!!!!!!!!!!!!");
		System.out.println("ㅁㄴ앎ㄴㅇㄹ" + cartIds);
		return "redirect:/cart/listLookUp";
	}
}
