package com.ky.eatsorder.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ky.eatsorder.domain.coupon.CouponDetailVO;
import com.ky.eatsorder.domain.coupon.OwnedCouponVO;
import com.ky.eatsorder.mapper.CouponMapper;
import com.ky.eatsorder.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponMapper mapper;
	
	@Override
	public boolean checkAvailableCoupon(OwnedCouponVO coupon) {
		// 사용 가능한 쿠폰인지 확인
		return mapper.checkAvailableCoupon(coupon);
	}

	@Override
	public int useCoupon(OwnedCouponVO ownedCoupon) {
		// 쿠폰 사용
		int result = mapper.useCoupon(ownedCoupon);
		System.out.println("쿠폰 사용 결과 : " + result);
		
		return result;
	}

	@Override
	public ArrayList<CouponDetailVO> getCoupons(String owner_id) {
		// 쿠폰 목록 조회
		return mapper.getCoupons(owner_id);
	}

	@Override
	public int getCouponCount(String owner_id) {
		// 사용 가능한 쿠폰 개수 조회
		return mapper.getCouponCount(owner_id);
	}

}
