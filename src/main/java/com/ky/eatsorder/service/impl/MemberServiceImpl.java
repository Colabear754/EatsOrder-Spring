package com.ky.eatsorder.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ky.eatsorder.domain.member.MemberInfoVO;
import com.ky.eatsorder.domain.member.MemberLoginVO;
import com.ky.eatsorder.mapper.MemberMapper;
import com.ky.eatsorder.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;

	@Override
	public boolean login(MemberLoginVO member) {
		// 로그인
		boolean result = mapper.login(member);
		System.out.println("로그인 결과 : " + result);

		return result;
	}

	@Override
	public boolean checkDuplicateMember(HashMap<String, String> map) {
		// 회원 중복확인
		boolean result = mapper.checkDuplicateMember(map);
		System.out.println("중복확인 결과 : " + result);

		return result;
	}

	@Override
	public int registInfo(MemberInfoVO member) {
		// 회원 가입 1단계(회원 정보 등록)
		int result = mapper.registInfo(member);
		System.out.println("회원 정보 등록 결과 : " + result);
		
		return result;
	}

	@Override
	public int registLogin(MemberLoginVO member) {
		// 회원 가입 2단계(로그인 정보 등록)
		int result = mapper.registLogin(member);
		System.out.println("로그인 정보 등록 결과 : " + result);
		
		return result;
	}
	
	@Override
	public int deleteLogin(String email) {
		// 회원 탈퇴 1단계(로그인 정보 삭제)
		int result = mapper.deleteLogin(email);
		System.out.println("로그인 정보 삭제 결과 : " + result);
		
		return result;
	}

	@Override
	public int updateWithdraw_date(String email) {
		// 회원 탈퇴 2단계(탈퇴일 등록)
		int result = mapper.updateWithdraw_date(email);
		System.out.println("탈퇴일 등록 결과 : " + result);
		
		return result;
	}

	@Override
	public int insertWithdrawMember(HashMap<String, String> map) {
		// 회원 탈퇴 3단계(탈퇴 회원 목록에 등록)
		int result = mapper.insertWithdrawMember(map);
		System.out.println("탈퇴 회원 등록 결과 : " + result);
		
		return result;
	}

	@Override
	public int updateInfo(HashMap<String, Object> map) {
		// 회원정보 수정
		int result = mapper.updateInfo(map);
		System.out.println("회원정보 수정 결과 : " + result);

		return result;
	}

	@Override
	public int updatePassword(MemberLoginVO member) {
		// 회원 비밀번호 수정
		int result = mapper.updatePassword(member);
		System.out.println("회원 비밀번호 수정 결과 : " + result);

		return result;
	}

	@Override
	public int deleteMemberInfo() {
		// 보관기간이 지난 회원정보 삭제
		int result = mapper.deleteMemberInfo();
		System.out.println("삭제된 회원 정보 수 : " + result);

		return result;
	}

	@Override
	public MemberInfoVO getMember(String email) {
		// 회원정보 조회
		return mapper.getMember(email);
	}

	@Override
	public String getNickname(String email) {
		// 회원 닉네임 조회
		return mapper.getNickname(email);
	}

	@Override
	public String findEmail(String phone) {
		// 등록된 휴대폰 번호로 이메일 찾기
		return mapper.findEmail(phone);
	}

	@Override
	public boolean checkValidMember(MemberInfoVO member) {
		// 유효한 회원인지 확인
		return mapper.checkValidMember(member);
	}

	@Override
	public int resetPassword(MemberLoginVO member) {
		// 비밀번호 재설정
		int result = mapper.resetPassword(member);
		System.out.println("비밀번호 재설정 결과 : " + result);

		return result;
	}

	@Override
	public int updatePoint(HashMap<String, Object> map) {
		// 회원 포인트 수정
		int result = mapper.updatePoint(map);
		System.out.println("회원 포인트 수정 결과 : " + result);

		return result;
	}
}
