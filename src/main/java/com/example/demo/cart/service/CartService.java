package com.example.demo.cart.service;

import java.util.List;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.entity.Cart;
import com.example.demo.member.entity.Member;
import com.example.demo.title.entity.Title;

public interface CartService {

	default Cart dtoToEntity(CartDTO dto) {

		Member member = new Member();
		member.setMemberNo(dto.getMemberNo());

		Title title = new Title();
		title.setTNo(dto.getTNo());

		Cart cart = Cart.builder().memberNo(member).tNo(title).count(dto.getCount()).price(dto.getPrice()).build();
		return cart;
	}

	default Cart dtoToEntityDelete(CartDTO dto) {

		Member member = new Member();
		member.setMemberNo(dto.getMemberNo());

		Title title = new Title();
		title.setTNo(dto.getTNo());

		Cart cart = Cart.builder().cartNo(dto.getCartNo()).memberNo(member).tNo(title).count(dto.getCount())
				.price(dto.getPrice()).build();
		return cart;
	}

	default CartDTO entityToDTO(Cart cart) {
		CartDTO dto = CartDTO.builder().cartNo(cart.getCartNo()).memberNo(cart.getMemberNo().getMemberNo())
				.tNo(cart.getTNo().getTNo()).count(cart.getCount()).price(cart.getPrice()).build();
		return dto;
	}

	// 장바구니에 상품 추가
	void cartAdd(CartDTO dto);

	// 회원 장바구니 목록 조회
	List<CartDTO> listLookUp(int no);

	// 장바구니 조회
	CartDTO cartLookUp(int no);

	// 장바구니 (수량) 수정
	void modify(CartDTO dto);

	// 장바구니에서 특정 목록 선택
	List<CartDTO> cartSelect(List<Integer> cartNo);

	// 장바구니에서 특정 상품 제거
	void cartClearByDTOList(List<CartDTO> list);
}
