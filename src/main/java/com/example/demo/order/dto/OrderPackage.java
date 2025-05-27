package com.example.demo.order.dto;

import java.util.List;

import com.example.demo.orderItem.dto.OrderItemDTO;
import com.example.demo.title.dto.TitleDTO;

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
public class OrderPackage {

	OrderDTO orderDTO;
	List<OrderItemTitle> orderTitleList;
	
	@Getter
	@Setter
	@Builder
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OrderItemTitle{
		OrderItemDTO orderItemDTO;
		TitleDTO titleDTO;
	}
}
