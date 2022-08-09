package com.ky.eatsorder.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.ky.eatsorder.domain.menu.OptionInfoVO;
import com.ky.eatsorder.domain.order.CartVO;
import com.ky.eatsorder.domain.order.OrderDetailVO;
import com.ky.eatsorder.domain.order.OrderHistoryVO;

public interface OrderService {
	public int getRst_id(int menu_id);
	public boolean isExistDifferentRst(HashMap<String, Object> map);
	public boolean isExistItem(HashMap<String, Object> map);
	public int insertCartItem(HashMap<String, Object> map);
	public int insertCartItemOptions(int[] options);
	public ArrayList<CartVO> getCartItems(String orderer);
	public int deleteCartItem(HashMap<String, Object> map);
	public int updateCartItem(HashMap<String, Object> map);
	public int cleanCart(String orderer);
	public int insertOrder_history(OrderHistoryVO order);
	public int insertOrder_detail(HashMap<String, String> map);
	public int insertOrder_options(String orderer);
	public boolean isCancelable(HashMap<String, String> map);
	public int cancelOrder(HashMap<String, String> map);
	public ArrayList<OrderDetailVO> getOrderedItemList(String order_number);
	public OrderHistoryVO getOrderHistory(String order_number);
	public ArrayList<HashMap<String, Object>> getOrderList(HashMap<String, Object> map);
	public ArrayList<String> getMenu_name(String order_number);
	public int getDelivery_tip(int menu_id);
	public ArrayList<String> getOrderedItems(HashMap<String, Object> map);
	public ArrayList<OptionInfoVO> getSelectedOptions(int bundle_id);
	public ArrayList<OptionInfoVO> getOrderedOptions(int bundle_id);
}
