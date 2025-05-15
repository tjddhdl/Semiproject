package com.example.demo.title.dto;

import java.time.LocalDate;

import com.example.demo.title.entity.Category;
import com.example.demo.title.entity.ModelName;

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
	Category category;
	ModelName model;
	Integer price;
	Integer ageRate;
	LocalDate releaseDate;
	String image;
	Integer stock;
}
