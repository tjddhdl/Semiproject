package com.example.demo.orderItem.entity;

import com.example.demo.order.entity.Order;
import com.example.demo.title.entity.Title;

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
@Table(name = "tbl_order_item")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderItemNo;

	@ManyToOne
	@JoinColumn(name = "order_no")
	Order orderNo;

	@ManyToOne
	@JoinColumn(name = "t_no")
	Title tNo;

	@Column(nullable = false)
	int count;

	@Column(nullable = false)
	int price;
}
