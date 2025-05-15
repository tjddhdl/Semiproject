package com.example.demo.order.entity;

import java.time.LocalDateTime;

import com.example.demo.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tbl_order")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderNo;

	@ManyToOne
	@JoinColumn(name = "member_no")
	Member memberNo;

	@Column(nullable = false)
	int totalCount;

	@Column(nullable = false)
	int totalPrice;

	LocalDateTime arrivedDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Status status;
}
