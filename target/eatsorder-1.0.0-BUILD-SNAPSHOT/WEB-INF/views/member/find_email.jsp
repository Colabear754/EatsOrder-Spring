<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="search_wrapper">
    <div class="search_box">
        <h1>이메일 찾기</h1>
        <p>휴대폰 번호</p>
        <input type="text" name="phone" id="phone" oninput="this.value = this.value.replaceAll(/\D/g, '')" placeholder="휴대폰 번호를 입력해주세요." maxlength="13">
        <div id="error">휴대폰 번호를 입력해주세요.</div>
    </div>
    <div class="search_btn_box">
        <input type="button" id="submit" value="찾기">
    </div>
</div>
<div id="result_wrapper">
	<p id="result"></p>
	<a class="button" id="login_button" href="/eatsorder/member/login">로그인 하러 가기</a>
	<a class="button" id="regist_button" href="/eatsorder/member/create_account">회원 가입</a>
</div>