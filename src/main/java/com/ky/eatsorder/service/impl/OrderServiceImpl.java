package com.ky.eatsorder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ky.eatsorder.domain.menu.OptionInfoVO;
import com.ky.eatsorder.domain.order.CartVO;
import com.ky.eatsorder.domain.order.OrderDetailVO;
import com.ky.eatsorder.domain.order.OrderHistoryVO;
import com.ky.eatsorder.mapper.OrderMapper;
import com.ky.eatsorder.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper mapper;

	@Override
	public int getRst_id(int menu_id) {
		// 매장ID 조회
		return mapper.getRst_id(menu_id);
	}

	@Override
	public boolean isExistDifferentRst(HashMap<String, Object> map) {
		// 장바구니에 다른 매장이 존재하는지 검사
		return mapper.isExistDifferentRst(map);
	}

	@Override
	public boolean isExistItem(HashMap<String, Object> map) {
		// 장바구니에 이미 존재하는 메뉴인지 검사
		return mapper.isExistItem(map);
	}
	
	@Override
	public int insertCartItem(HashMap<String, Object> map) {
		// 장바구니에 메뉴 추가 1단계(메뉴 추가)
		int result = mapper.insertCartItem(map);
		System.out.println("메뉴 추가 결과 : " + result);
		
		return result;
	}

	@Override
	public int insertCartItemOptions(List<String> options) {
		// 장바구니에 메뉴 추가 2단계(옵션 추가)
		return mapper.insertCartItemOptions(options);
	}

	@Override
	public ArrayList<CartVO> getCartItems(String orderer) {
		// 장바구니 조회
		return mapper.getCartItems(orderer);
	}

	@Override
	public int deleteCartItem(HashMap<String, Object> map) {
		// 장바구니 메뉴 삭제
		int result = mapper.deleteCartItem(map);
		System.out.println("메뉴 삭제 결과 : " + result);
		
		return result;
	}

	@Override
	public int updateCartItem(HashMap<String, Object> map) {
		// 장바구니에 추가된 메뉴 수량 변경
		int result = mapper.updateCartItem(map);
		System.out.println("장바구니 수정 결과 : " + result);
		
		return result;
	}

	@Override
	public int cleanCart(String orderer) {
		// 장바구니 비우기
		int result = mapper.cleanCart(orderer);
		System.out.println("삭제된 메뉴 개수 : " + result);
		
		return result;
	}

	@Override
	public int insertOrder_history(OrderHistoryVO order) {
		// 주문하기 1단계(주문내역 등록)
		int result = mapper.insertOrder_history(order);
		System.out.println("주문내역 등록 결과 : " + result);
		
		return result;
	}

	@Override
	public int insertOrder_detail(HashMap<String, Object> map) {
		// 주문하기 2단계(주문상세 등록)
		int result = mapper.insertOrder_detail(map);
		System.out.println("주문상세 등록 결과 : " + result);
		
		return result;
	}

	@Override
	public int insertOrder_options(String orderer) {
		// 주문하기 3단계(주문옵션 등록)
		int result = mapper.insertOrder_options(orderer);
		System.out.println("주문옵션 등록 결과 : " + result);
		
		return result;
	}

	@Override
	public boolean isCancelable(HashMap<String, Object> map) {
		// 취소 가능한 주문인지 확인
		return mapper.isCancelable(map);
	}

	@Override
	public String getPay_date(String order_number) {
		// 주문번호의 결제시간 조회
		return mapper.getPay_date(order_number);
	}

	@Override
	public int cancelOrder(HashMap<String, Object> map) {
		// 주문취소
		int result = mapper.cancelOrder(map);
		System.out.println("주문취소 결과 : " + result);
		
		return result;
	}

	@Override
	public ArrayList<OrderDetailVO> getOrderedItemList(String order_number) {
		// 주문한 메뉴 목록 조회
		return mapper.getOrderedItemList(order_number);
	}

	@Override
	public OrderHistoryVO getOrderHistory(String order_number) {
		// 주문 내역 조회
		return mapper.getOrderHistory(order_number);
	}

	@Override
	public ArrayList<HashMap<String, Object>> getOrderList(HashMap<String, Object> map) {
		// 주문내역 목록 조회
		return mapper.getOrderList(map);
	}

	@Override
	public ArrayList<String> getMenu_name(String order_number) {
		// 주문 번호에 해당하는 대표 메뉴 이름 조회
		return mapper.getMenu_name(order_number);
	}

	@Override
	public int getDelivery_tip(int menu_id) {
		// 메뉴에 해당하는 매장 배달팁 조회
		return mapper.getDelivery_tip(menu_id);
	}

	@Override
	public ArrayList<String> getOrderedItems(HashMap<String, Object> map) {
		// 주문한 메뉴 이름 목록 조회
		// keyType : review_number, order_number
		return mapper.getOrderedItems(map);
	}

	@Override
	public ArrayList<OptionInfoVO> getSelectedOptions(int bundle_id) {
		// 선택한 옵션 조회
		return mapper.getSelectedOptions(bundle_id);
	}

	@Override
	public ArrayList<OptionInfoVO> getOrderedOptions(int bundle_id) {
		// 주문한 옵션 조회
		return mapper.getOrderedOptions(bundle_id);
	}
}
