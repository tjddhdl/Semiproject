package com.example.demo.order.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Getter
public class BaseEntity {

	@CreatedDate
	LocalDateTime orderDate;

	@PrePersist
	void onCreate() {
		this.orderDate = LocalDateTime.now().withSecond(0).withNano(0);
	}
}
