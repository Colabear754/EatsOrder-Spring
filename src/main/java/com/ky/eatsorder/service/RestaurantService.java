package com.ky.eatsorder.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.ky.eatsorder.domain.restaurant.RestaurantVO;

public interface RestaurantService {
	public RestaurantVO getRestaurant(int rst_id);
	public ArrayList<RestaurantVO> getRestaurants(HashMap<String, Object> map);
	public boolean isFavorite(HashMap<String, Object> map);
	public int registFavoriteRestaurant(HashMap<String, Object> map);
	public int removeFavoriteRestaurant(HashMap<String, Object> map);
	public ArrayList<RestaurantVO> getFavoriteRestaurants(HashMap<String, Object> map);
	public double getRating(int rst_id);
	public RestaurantVO getRestaurantOfMenu(int menu_id);
	public int getRestaurantCount(HashMap<String, Object> map);
}
