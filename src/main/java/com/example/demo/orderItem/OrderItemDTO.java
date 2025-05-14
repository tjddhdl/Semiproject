package com.example.demo.orderItem;

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
public class OrderItemDTO {

	int orderItemNo;
	int orderNo;
	int tNo;
	Integer count;
	Integer price;
}
