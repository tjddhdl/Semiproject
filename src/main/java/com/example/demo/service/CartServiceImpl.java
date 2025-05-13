package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDTO;
import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	// 장바구니에 상품 추가
	@Override
	public void cartAdd(int memberNo, int tNo, int count, int price) {
		Cart cart = Cart.builder().memberNo(memberNo).tNo(tNo).count(count).price(price).build();
		cartRepository.save(cart);
	}

	// 장바구니 조회
	@Override
	public CartDTO cartLookUp(int no) {
		Optional<Cart> optional = cartRepository.findById(no);
		if (optional.isPresent()) {
			CartDTO dto = entityToDTO(optional.get());
			return dto;
		}
		return null;
	}

	// 장바구니에서 특정 목록 선택
	@Override
	public List<CartDTO> cartSelect(List<Integer> cartNo) {
		List<Cart> list = cartRepository.findAllById(cartNo);
		List<CartDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
	}

	// 장바구니에서 특정 상품 제거
	@Override
	public void cartClearByIdList(List<Integer> cartNo) {
		cartRepository.deleteAllById(cartNo);
	}

}
