package com.example.demo.cart;

import com.example.demo.member.Member;
import com.example.demo.title.Title;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_cart")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cartNo;

	@ManyToOne
	@JoinColumn(name = "member_no")
	Member memberNo;

	@ManyToOne
	@JoinColumn(name = "t_no")
	Title tNo;

	@Column(nullable = false)
	int count;

	@Column(nullable = false)
	int price;
}
