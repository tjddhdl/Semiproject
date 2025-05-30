package com.example.demo.title.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.example.demo.title.entity.Title;

public interface TitleRepository extends JpaRepository<Title, Integer>, QuerydslPredicateExecutor<Title> {

	List<Title> findByTitleNameContaining(String titleName);

	@Query(value = "select * from tbl_title where (:model is null or model = :model) "
			+ " and (:category is null or category = :category) " + " and (:minAge is null or age_rate >= :minAge) "
			+ " and (:maxAge is null or age_rate <= :maxAge)", nativeQuery = true)
	List<Title> findTitleByFilter(@Param("model") String model, @Param("category") String category,
			@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);
}