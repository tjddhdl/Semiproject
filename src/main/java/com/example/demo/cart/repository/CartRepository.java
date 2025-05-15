package com.example.demo.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query(value = "select * from tbl_cart where member_no = :memberNo and t_no = :tNo limit 1 ", nativeQuery = true)
	Optional<Cart> findOneByMemberNoAndTNo(@Param("memberNo") int memberNo, @Param("tNo") int tNo);
}
