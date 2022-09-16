package com.ky.eatsorder.domain.etc_classes;

import java.util.ArrayList;

import com.ky.eatsorder.domain.menu.OptionInfoVO;

public class CartItem {
	private int menu_id;
	private String menu_name;
	private ArrayList<OptionInfoVO> selectedOptions;
	private int quantity;
	private int price;

	public CartItem() {
		super();
	}

	public CartItem(int menu_id, String menu_name, ArrayList<OptionInfoVO> selectedOptions, int quantity, int price) {
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.selectedOptions = selectedOptions;
		this.quantity = quantity;
		this.price = price;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public ArrayList<OptionInfoVO> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(ArrayList<OptionInfoVO> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
