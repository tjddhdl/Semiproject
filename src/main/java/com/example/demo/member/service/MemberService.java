package com.example.demo.member.service;

import java.util.List;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;

public interface MemberService {

	default Member dtoToEntity(MemberDTO dto) {
		Member member = Member.builder().id(dto.getId()).password(dto.getPassword()).userName(dto.getUserName())
				.age(dto.getAge()).role(dto.getRole()).address(dto.getAddress()).build();
		return member;
	}

	default MemberDTO entityToDTO(Member m) {

		MemberDTO dto = MemberDTO.builder().memberNo(m.getMemberNo()).id(m.getId()).password(m.getPassword())
				.userName(m.getUserName()).age(m.getAge()).role(m.getRole()).address(m.getAddress()).build();
		return dto;
	}

	// 회원가입
	void register(MemberDTO dto);

	// 회원목록조회
	List<MemberDTO> listLookUp();

	// 회원정보조회
	MemberDTO lookUp(int no);

	// 회원정보수정
	void modify(MemberDTO dto);

	// 아이디 체크
	boolean idCheck(String id);
	
	// 로그인용 아이디로 검색
	MemberDTO lookUpId(String id);
	
	// 회원삭제
	void delete(int id);
}
