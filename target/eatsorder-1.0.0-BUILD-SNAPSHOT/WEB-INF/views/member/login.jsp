<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${eatsorder_uid != null}">
	<meta http-equiv="Refresh" content="0;url=/eatsorder/main">
</c:if>
<div class="login_wrapper">
	<div class="login_box">
		<h1>로그인</h1>
		<input type="text" name="account" id="account" placeholder="이메일을 입력해주세요"> 
		<input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요">
		<div id="login_error">계정 또는 비밀번호를 확인해주세요.</div>
		<div class="id_pw_search">
			<a href="/eatsorder/member/find_email">이메일 찾기</a> <span> | </span> <a href="/eatsorder/member/find_password">비밀번호 찾기</a>
		</div>
		<div class="login_btn_box">
			<input type="button" name="login" id="login" value="로그인">
			<a href="/eatsorder/member/create_account">회원가입</a>
		</div>
	</div>
</div>