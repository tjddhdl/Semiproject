package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

import jakarta.transaction.Transactional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByOrderNo(Order orderNo);

	@Transactional
	@Modifying
	@Query(value = "delete from tbl_order_item where order_no = :orderNo", nativeQuery = true)
	void deleteAllByOrderNo(@Param("orderNo") int orderNo);
}
