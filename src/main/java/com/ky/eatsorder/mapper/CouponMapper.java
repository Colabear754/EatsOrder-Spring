package com.ky.eatsorder.mapper;

import java.util.ArrayList;

import com.ky.eatsorder.domain.coupon.CouponDetailVO;
import com.ky.eatsorder.domain.coupon.OwnedCouponVO;

public interface CouponMapper {
	public boolean checkAvailableCoupon(OwnedCouponVO coupon);
	public int useCoupon(OwnedCouponVO ownedCoupon);
	public ArrayList<CouponDetailVO> getCoupons(String owner_id);
	public int getCouponCount(String owner_id);
}
