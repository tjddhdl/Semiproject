package com.example.demo.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	// 회원가입
	@Override
	public void register(MemberDTO dto) {
		// 중복체크
		if (memberRepository.findByMemberId(dto.getId()) != null)
			throw new IllegalArgumentException("이미 사용 중인 아이디입니다");
		Member member = dtoToEntity(dto);
		memberRepository.save(member);
	}

	// 회원목록조회
	@Override
	public List<MemberDTO> listLookUp() {
		List<Member> list = memberRepository.findAll();
		List<MemberDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
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

	// 아이디로 회원정보유무 체크(중복이 있으면 true)
	@Override
	public boolean idCheck(String id) {
		if (memberRepository.findByMemberId(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	// 회원정보수정
	@Override
	public void modify(MemberDTO dto) {
		Optional<Member> optional = memberRepository.findById(dto.getMemberNo());
		if (optional.isPresent()) {
			Member member = optional.get();

			// 중복체크 (자신은 예외)
			Member memberCheck = memberRepository.findByMemberId(dto.getId());
			if (memberCheck != null && memberCheck.getMemberNo() != dto.getMemberNo())
				throw new IllegalArgumentException("이미 사용 중인 아이디입니다");
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
