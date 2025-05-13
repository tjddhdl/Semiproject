package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	// 회원가입
	@Override
	public void register(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		memberRepository.save(member);

	}

	// 회원정보조회
	@Override
	public MemberDTO lookUp(int no) {
		Optional<Member> optional = memberRepository.findById(no);
		if (optional.isPresent()) {

			Member member = optional.get();
			MemberDTO dto = entityToDTO(member);
			return dto;
		}
		return null;
	}

	// 회원정보수정
	@Override
	public void modify(MemberDTO dto) {
		Optional<Member> optional = memberRepository.findById(dto.getMemberNo());
		if (optional.isPresent()) {
			Member member = optional.get();
			if (dto.getId() != null)
				member.setId(dto.getId());
			if (dto.getPassword() != null)
				member.setPassword(dto.getPassword());
			if (dto.getUserName() != null)
				member.setUserName(dto.getUserName());
			if (dto.getAge() != null)
				member.setAge(dto.getAge());
			if (dto.getRole() != null)
				member.setRole(dto.getRole());
			if (dto.getAddress() != null)
				member.setAddress(dto.getAddress());
			memberRepository.save(member);
		}
	}

	// 회원삭제
	@Override
	public void delete(int id) {
		memberRepository.deleteById(id);
	}

}
