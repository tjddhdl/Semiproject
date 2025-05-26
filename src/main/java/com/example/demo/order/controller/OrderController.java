package com.example.demo.order.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;
import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.service.OrderService;
import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.service.TitleService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	CartService cartService;

	@Autowired
	OrderService orderService;

	@Autowired
	TitleService titleService;

	@Autowired
	MemberService memberService;

	// 카트목록 -> 주문 확인 페이지
	@PostMapping("/orderCheck")
	public String order(@RequestParam(name = "cartIds") List<Integer> cartIds, Model model) {
//		attributes.addFlashAttribute("cartIds", cartIds);
		List<CartDTO> list = cartService.cartSelect(cartIds);
		List<Map<String, Object>> cartTitleList = new ArrayList<>();
		int all = 0;
		for (CartDTO dto : list) {
			all += dto.getCount() * dto.getPrice();
			TitleDTO titleDTO = titleService.lookUp(dto.getTNo());
			Map<String, Object> map = new HashMap<>();
			map.put("cart", dto);
			map.put("title", titleDTO);
			cartTitleList.add(map);
		}
		model.addAttribute("list", cartTitleList);
		model.addAttribute("all", all);
		return "/order/orderCheck";
	}

	// 주문 완료 페이지
	@PostMapping("/orderComplete")
	public String orderComplete(@RequestParam(name = "cartIds") List<Integer> cartIds) {
		List<CartDTO> list = cartService.cartSelect(cartIds);
		orderService.orderAdd(list);
		return "redirect:/order/orderComplete";
	}

	@GetMapping("/orderComplete")
	public void orderCOmplete() {
	}

	// 주문 이력 페이지
	@GetMapping("/orderLookUp")
	public void orderLookUp(Principal principal, Model model) {
		MemberDTO dto = memberService.lookUpId(principal.getName());
		List<OrderDTO> list = orderService.orderSearch(dto.getMemberNo());
		System.out.println(list);
		model.addAttribute("list", list);
	}

}
