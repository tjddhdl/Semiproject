package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.enumfolder.Status;

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
public class OrderDTO {

	int orderNo;
	int memberNo;
	Integer totalCount;
	Integer totalPrice;
	LocalDateTime orderDate;
	LocalDateTime arrivedDate;
	Status status;
}
