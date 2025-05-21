package com.example.demo.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Role;
import com.example.demo.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;

	// 로그인페이지
	@GetMapping("/login")
	public void login() {
	}

	// 회원가입 페이지
	@GetMapping("/register")
	public void register(Model model) {
		model.addAttribute("roles", Role.values());
	}

	// 에러반환시 페이지 처리해야함
	// 회원가입 페이지 반환
	// 회원가입시 권한 무조건 고객으로
	@PostMapping("/register")
	public String registerPost(MemberDTO dto) {
		dto.setRole(Role.Customer);
		service.register(dto);
		return "redirect:/member/login";
	}

	// 로그인한 자신만 접근 가능하도록 처리해야함
	// 회원정보조회
	// 카트랑 주문목록도 볼 수 있도록 전반적으로 뒤집어야함
	@GetMapping("/lookUp")
	public void lookUp(@RequestParam(name = "no") int no, Model model) {
		MemberDTO dto = service.lookUp(no);
		model.addAttribute("dto", dto);
	}

	// 관리자 권한일 때 가능하도록 처리해야 함
	// 회원목록조회
	@GetMapping("/listLookUp")
	public void listLookUp(Model model) {
		List<MemberDTO> list = service.listLookUp();
		model.addAttribute("list", list);
	}

	// 회원정보수정(회원용)
	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		MemberDTO dto = service.lookUp(no);
		model.addAttribute("dto", dto);
		model.addAttribute("roles", Role.values());
	}

	// 로그인한 본인일 때 가능하도록 처리해야함
	// 나이, 권한 수정 못함
	// 회원정보수정 반환(회원용)
	@PostMapping("/modify")
	public String modifyPost(MemberDTO dto, RedirectAttributes attributes) {
		MemberDTO dtoToApply = service.lookUp(dto.getMemberNo());
		dto.setAge(dtoToApply.getAge());
		dto.setRole(dtoToApply.getRole());
		service.modify(dto);
		attributes.addAttribute("no", dto.getMemberNo());
		return "redirect:/member/lookUp";
	}

	// 회원정보수정(관리자용)
	@GetMapping("/modifyAdmin")
	public void modifyAdmin(@RequestParam(name = "no") int no, Model model) {
		MemberDTO dto = service.lookUp(no);
		model.addAttribute("dto", dto);
		model.addAttribute("roles", Role.values());
	}

	@PostMapping("/modifyAdmin")
	public String modifyAdminPost(MemberDTO dto) {
		service.modify(dto);
		return "redirect:/member/listLookUp";
	}

	// 회원삭제
	@PostMapping("/delete")
	public String delete(MemberDTO dto) {
		System.out.println("뭐야뭐야뭐야"+dto);
		service.delete(dto.getMemberNo());
		return "redirect:/title/main";
	}

	// 회원가입 중복체크용 ajax 회원검색
	// 중복이면 true
	@ResponseBody
	@GetMapping("/check-id")
	public Map<String, Boolean> idCheck(@RequestParam(name = "id") String id) {
		boolean result = service.idCheck(id);
		System.out.println("결과값~~~" + result);
		return Map.of("duplicate", result);
	}
}
