package com.example.demo.member.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int memberNo;

	@Column(length = 30, nullable = false)
	String id;

	@Column(length = 300, nullable = false)
	String password;

	@Column(length = 20, nullable = false)
	String userName;

	@Column(nullable = false)
	LocalDate age;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Role role;

	@Column(length = 50)
	String address;
}
