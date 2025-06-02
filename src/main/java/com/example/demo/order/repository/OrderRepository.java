package com.example.demo.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.order.entity.Order;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(value = "select * from tbl_order where member_no = :memberNo", nativeQuery = true)
	List<Order> findAllByMemberNo(@Param("memberNo") int memberNo);

	@Transactional
	@Modifying
	@Query(value = "delete from Order or where or.orderNo = :orderNo")
	void deleteByOrderNo(@Param("orderNo") int orderNo);
	
	List<Order> findAll();
}
