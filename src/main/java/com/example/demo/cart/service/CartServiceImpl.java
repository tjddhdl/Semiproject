package com.example.demo.cart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.entity.Title;
import com.example.demo.title.repository.TitleRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	TitleRepository titleRepository;

	// 장바구니에 상품 추가
	@Override
	public void cartAdd(CartDTO dto) {
		Optional<Title> optional = titleRepository.findById(dto.getTNo());
		Title title = optional.get();

		// 동일 사용자가 동일 상품을 추가할 시 기존 장바구니에 수량 추가
		// 그렇지 않으면 새 장바구니로 추가
		Optional<Cart> optional2 = cartRepository.findOneByMemberNoAndTNo(dto.getMemberNo(), dto.getTNo());
		if (optional2.isPresent()) {
			Cart cart = optional2.get();
			cart.setCount(cart.getCount() + dto.getCount());

			// 상품 재고 확인
			if (title.getStock() < cart.getCount()) {
				throw new IllegalStateException("상품 재고 부족: " + title.getTitleName());
			}

			cart.setPrice(title.getPrice());
			cartRepository.save(cart);

		} else {
			// 상품 재고 확인
			if (title.getStock() < dto.getCount()) {
				throw new IllegalStateException("상품 재고 부족: " + title.getTitleName());
			}

			Cart cart = dtoToEntity(dto);
			cart.setPrice(title.getPrice());
			cartRepository.save(cart);
		}

	}

	// 회원 장바구니 목록 조회
	@Override
	public List<CartDTO> listLookUp(int no) {
		List<Cart> list = cartRepository.findAllbyMemberNo(no);
		List<CartDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
	}

	// 단건 조회는 사용할 일이 없을 것 같음
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

	// 바로구매용도임
	// 멤버 no와 타이틀 no로 특정 장바구니 조회
	@Override
	public CartDTO lookUpMNoTNo(int memberNo, int tNo) {
		Cart cart = cartRepository.findOneByMemberNoAndTNo(memberNo, tNo).get();
		CartDTO dto = entityToDTO(cart);
		return dto;
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
	public void cartClearByDTOList(List<CartDTO> list) {
		List<Cart> cartList = list.stream().map(dto -> dtoToEntityDelete(dto)).collect(Collectors.toList());
		cartRepository.deleteAll(cartList);
	}

	// 장바구니 (수량) 수정
	@Override
	public void modify(CartDTO dto) {
		Optional<Cart> optional = cartRepository.findById(dto.getCartNo());
		if (optional.isPresent()) {
			Cart cart = optional.get();
			Title title = titleRepository.findById(dto.getTNo()).get();
			if (dto.getCount() > title.getStock())
				throw new IllegalStateException("상품 재고 부족: " + title.getTitleName());
			cart.setCount(dto.getCount());
			cart.setPrice(title.getPrice());
			cartRepository.save(cart);
		}
	}

}
