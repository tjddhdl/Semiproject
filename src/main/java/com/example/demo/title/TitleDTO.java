package com.example.demo.title;

import java.time.LocalDate;

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
public class TitleDTO {

	int tNo;
	String titleName;
	Integer price;
	Model model;
	LocalDate releaseDate;
	Integer stock;
	String image;
	Integer ageRate;
	Category category;
}
