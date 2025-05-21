package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Role;
import com.example.demo.member.service.MemberService;
import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.entity.Category;
import com.example.demo.title.entity.ModelName;
import com.example.demo.title.service.TitleService;

@Controller
public class TempController {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	TitleService service;

	@Autowired
	MemberService memberService;

	@GetMapping("/main")
	public void main(Model model) {
		List<TitleDTO> list = service.main();
		model.addAttribute("list", list);
	}

	// 끝
	@GetMapping("/login")
	public void login() {

	}

	// 끝
	@GetMapping("register")
	public void register(Model model) {
		model.addAttribute("roles", Role.values());
	}

	// 끝
	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		MemberDTO dto = memberService.lookUp(no);
		model.addAttribute("dto", dto);
		model.addAttribute("roles", Role.values());
	}

	// 끝
	@GetMapping("/modifyAdmin")
	public void modifyAdmin(@RequestParam(name = "no") int no, Model model) {
		MemberDTO dto = memberService.lookUp(no);
		model.addAttribute("dto", dto);
		model.addAttribute("roles", Role.values());
	}

	@PostMapping("/modifyAdmin")
	public void modify(MemberDTO dto) {
		System.out.println("와아아아아아악" + dto);
	}

	@GetMapping("/listLookUp")
	public void listLookUp(Model model) {
		List<MemberDTO> list = memberService.listLookUp();
		model.addAttribute("list", list);
	}

	@GetMapping("/lookUp")
	public void lookUp(@RequestParam(name = "no") int no, Model model) {
		TitleDTO dto = service.lookUp(no);
		model.addAttribute("dto", dto);
		System.out.println("컨트롤러어" + dto);
	}

}
