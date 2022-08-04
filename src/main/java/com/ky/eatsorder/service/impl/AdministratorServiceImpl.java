package com.ky.eatsorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ky.eatsorder.domain.administrator.AdministratorVO;
import com.ky.eatsorder.mapper.AdministratorMapper;
import com.ky.eatsorder.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdministratorMapper mapper;
	
	@Override
	public boolean adminLogin(AdministratorVO admin) {
		// 관리자 로그인
		boolean result = mapper.adminLogin(admin);
		System.out.println("관리자 로그인 결과 : " + result);
		
		return result;
	}

}
