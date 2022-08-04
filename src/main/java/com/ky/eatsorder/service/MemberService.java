package com.ky.eatsorder.service;

import java.util.HashMap;

import com.ky.eatsorder.domain.member.MemberInfoVO;
import com.ky.eatsorder.domain.member.MemberLoginVO;

public interface MemberService {
	public boolean login(MemberLoginVO member);
	public boolean checkDuplicateMember(HashMap<String, String> map);
	public int registInfo(MemberInfoVO member);
	public int registLogin(MemberLoginVO member);
	public int deleteLogin(String email);
	public int updateWithdraw_date(String email);
	public int insertWithdrawMember(HashMap<String, String> map);
	public int updateInfo(HashMap<String, Object> map);
	public int updatePassword(MemberLoginVO member);
	public int deleteMemberInfo();
	public MemberInfoVO getMember(String email);
	public String getNickname(String email);
	public String findEmail(String phone);
	public boolean checkValidMember(MemberInfoVO member);
	public int resetPassword(MemberLoginVO member);
	public int updatePoint(HashMap<String, Object> map);
}
