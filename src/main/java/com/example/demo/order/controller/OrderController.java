package com.example.demo.order.controller;

import java.security.Principal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;
import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.dto.OrderPackage;
import com.example.demo.order.dto.OrderPackage.OrderItemTitle;
import com.example.demo.order.entity.Status;
import com.example.demo.order.service.OrderService;
import com.example.demo.orderItem.dto.OrderItemDTO;
import com.example.demo.orderItem.service.OrderItemService;
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
	OrderItemService itemService;

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

	// 타이틀-> 바로 구매 -> 주문 확인 페이지
	@PostMapping("/buy")
	public String buy(CartDTO dto, Principal principal, Model model) {
		MemberDTO memberDTO = memberService.lookUpId(principal.getName());
		dto.setMemberNo(memberDTO.getMemberNo());
		cartService.cartAdd(dto);
		// 카트서비스에서 타이틀 이름으로 카트를 찾는 걸 만들자
		CartDTO cartDTO = cartService.lookUpMNoTNo(memberDTO.getMemberNo(), dto.getTNo());
		List<Map<String, Object>> cartTitleList = new ArrayList<>();
		int all = 0;
		all += cartDTO.getCount() * cartDTO.getPrice();
		TitleDTO titleDTO = titleService.lookUp(cartDTO.getTNo());
		Map<String, Object> map = new HashMap<>();
		map.put("cart", cartDTO);
		map.put("title", titleDTO);
		cartTitleList.add(map);
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
		// 전체 정보 담을 dto list
		List<OrderPackage> list = new ArrayList<>();

		// 멤버정보로 order목록 구하기
		MemberDTO dto = memberService.lookUpId(principal.getName());
		List<OrderDTO> orderDTOList = orderService.orderSearch(dto.getMemberNo());

		// order묶음 각각마다 order상세 타이틀 엮어서 package 리스트에 넣기
		for (OrderDTO orderDTO : orderDTOList) {

			// 개별 패키지에 오더dto 넣기
			OrderPackage orderPackage = new OrderPackage();
			orderPackage.setOrderDTO(orderDTO);

			// 개별 패키지에 넣을 오더아이템-타이틀 리스트 만들기
			List<OrderItemTitle> oTList = new ArrayList<>();

			// 오더아이템-타이틀을 구해서 oTList에 넣기
			List<OrderItemDTO> itemList = itemService.orderItemLookUp(orderDTO.getOrderNo());
			for (OrderItemDTO itemDTO : itemList) {
				TitleDTO titleDTO = titleService.lookUp(itemDTO.getTNo());
				OrderItemTitle itemTitle = OrderItemTitle.builder().orderItemDTO(itemDTO).titleDTO(titleDTO).build();
				oTList.add(itemTitle);
			}

			// 개별 패키지에 oTList 넣기
			orderPackage.setOrderTitleList(oTList);

			// 마지막 리스트에 추가
			list.add(orderPackage);
		}
		model.addAttribute("list", list);
	}

	// 주문삭제
	@GetMapping("/orderDelete")
	public String orderDelete(@RequestParam(name = "orderNo") int orderNo, Principal principal) {
		String memberId = principal.getName();
		String idData = memberService.lookUp(orderService.orderLookUp(orderNo).getMemberNo()).getId();
		if (memberId.matches(idData)) {
			orderService.orderDelete(orderNo);
		}
		return "redirect:/order/orderLookUp";
	}

	// 주문취소
	@GetMapping("/orderCancel")
	public String orderCancel(@RequestParam(name = "orderNo") int orderNo, Principal principal) {
		String memberId = principal.getName();
		String idData = memberService.lookUp(orderService.orderLookUp(orderNo).getMemberNo()).getId();
		if (memberId.matches(idData)) {
			orderService.orderCancel(orderNo);
		}
		return "redirect:/order/orderLookUp";
	}

	// 주문 정보 수정(관리자)
	// 배송 관련 정보만 수정
	@GetMapping("/modify")
	public void orderModify(@RequestParam(name = "orderNo") int orderNo, Model model) {
		OrderDTO dto = orderService.orderLookUp(orderNo);
		dto.setOrderDate(dto.getOrderDate().truncatedTo(ChronoUnit.MINUTES));
		model.addAttribute("dto", dto);
		model.addAttribute("statuses", Status.values());
	}

	// 주문 정보 수정 post
	@PostMapping("/modify")
	public String modifyPost(OrderDTO dto) {
		System.out.println(dto);
		OrderDTO orderDTO = orderService.orderLookUp(dto.getOrderNo());
		orderDTO.setStatus(dto.getStatus());
		orderDTO.setArrivedDate(dto.getArrivedDate());
		orderService.orderModify(orderDTO);
		return "redirect:/title/main";
	}
}
