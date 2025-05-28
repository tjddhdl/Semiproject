package com.example.demo.orderItem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.order.entity.Order;
import com.example.demo.orderItem.entity.OrderItem;

import jakarta.transaction.Transactional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByOrderNo(Order orderNo);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM OrderItem oI WHERE oI.orderNo.orderNo = :orderNo")
	void deleteAllByOrderNo(@Param("orderNo") int orderNo);
}
