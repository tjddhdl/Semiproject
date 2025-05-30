package com.example.demo.member.dto;

import java.time.LocalDate;

import com.example.demo.member.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	int memberNo;
	String id;
	String password;
	String userName;
	LocalDate age;
	Role role;
	String address;
}
