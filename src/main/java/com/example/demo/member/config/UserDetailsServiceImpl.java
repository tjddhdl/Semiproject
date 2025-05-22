package com.example.demo.member.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.member.dto.CustomUser;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	MemberService service;

	@Override
	public UserDetails loadUserByUsername(String username) {
		MemberDTO dto = service.lookUpId(username);
		if (dto == null) {
			throw new UsernameNotFoundException(username);
		} else {
			return new CustomUser(dto);
		}
	}

}
