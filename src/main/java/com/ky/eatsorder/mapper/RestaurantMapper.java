package com.ky.eatsorder.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ky.eatsorder.domain.restaurant.FavoriteRestaurantVO;
import com.ky.eatsorder.domain.restaurant.RestaurantVO;

public interface RestaurantMapper {
	public RestaurantVO getRestaurant(int rst_id);
	public ArrayList<RestaurantVO> getRestaurantList(HashMap<String, Object> map);
	public boolean isFavorite(FavoriteRestaurantVO vo);
	public int registFavoriteRestaurant(FavoriteRestaurantVO vo);
	public int removeFavoriteRestaurant(FavoriteRestaurantVO vo);
	public ArrayList<RestaurantVO> getFavoriteRestaurants(String email);
	public double getRating(int rst_id);
	public RestaurantVO getRestaurantOfMenu(int menu_id);
	public int getRestaurantCount(HashMap<String, Object> map);
}
