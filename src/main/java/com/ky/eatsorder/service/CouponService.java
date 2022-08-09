package com.ky.eatsorder.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.ky.eatsorder.domain.coupon.OwnedCouponVO;

public interface CouponService {
	public boolean checkAvailableCoupon(OwnedCouponVO coupon);
	public int useCoupon(OwnedCouponVO ownedCoupon);
	public ArrayList<HashMap<String, Object>> getCoupons(String owner_id);
	public int getCouponCount(String owner_id);
}
