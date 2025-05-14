package com.example.demo.title;

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
@Table(name = "tbl_title")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Title {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int tNo;

	@Column(length = 50, nullable = false)
	String titleName;

	@Column(length = 30, nullable = false)
	int price;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Model model;

	LocalDate releaseDate;

	@Column(nullable = false)
	int stock;

	@Column(length = 100, nullable = true)
	String image;

	@Column(length = 10, nullable = false)
	int ageRate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Category category;
}
