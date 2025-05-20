package com.example.demo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query(value = "select * from tbl_member where id = :id limit 1", nativeQuery = true)
	Member findByMemberId(@Param("id") String id);
}
