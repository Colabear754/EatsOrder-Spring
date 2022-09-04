package com.ky.eatsorder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ky.eatsorder.domain.restaurant.FavoriteRestaurantVO;
import com.ky.eatsorder.domain.restaurant.RestaurantVO;
import com.ky.eatsorder.mapper.RestaurantMapper;
import com.ky.eatsorder.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantMapper mapper;
	
	@Override
	public RestaurantVO getRestaurant(int rst_id) {
		// 식당 정보 조회
		return mapper.getRestaurant(rst_id);
	}

	@Override
	public ArrayList<RestaurantVO> getRestaurantList(HashMap<String, Object> map) {
		// 식당 목록 조회
		return mapper.getRestaurantList(map);
	}

	@Override
	public boolean isFavorite(FavoriteRestaurantVO vo) {
		// 매장 좋아요 여부
		return mapper.isFavorite(vo);
	}

	@Override
	public int registFavoriteRestaurant(FavoriteRestaurantVO vo) {
		// 매장 찜하기
		int result = mapper.registFavoriteRestaurant(vo);
		System.out.println("찜 등록 결과 : " + result);
		
		return result;
	}

	@Override
	public int removeFavoriteRestaurant(FavoriteRestaurantVO vo) {
		// 매장 찜취소
		int result = mapper.removeFavoriteRestaurant(vo);
		System.out.println("찜 취소 결과 : " + result);
		
		return result;
	}

	@Override
	public ArrayList<RestaurantVO> getFavoriteRestaurants(String email) {
		// 찜한 매장 목록 조회
		return mapper.getFavoriteRestaurants(email);
	}

	@Override
	public double getRating(int rst_id) {
		// 매장 별점 조회
		return mapper.getRating(rst_id);
	}

	@Override
	public RestaurantVO getRestaurantOfMenu(int menu_id) {
		// 메뉴에 해당하는 매장 조회
		return mapper.getRestaurantOfMenu(menu_id);
	}

	@Override
	public int getRestaurantCount(HashMap<String, Object> map) {
		// 해당 지역의 매장 개수 조회
		return mapper.getRestaurantCount(map);
	}

}
