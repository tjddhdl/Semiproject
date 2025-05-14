package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(value = "select * from tbl_order where member_no = :memberNo", nativeQuery = true)
	List<Order> findAllByMemberNo(@Param("memberNo") int memberNo);
}
