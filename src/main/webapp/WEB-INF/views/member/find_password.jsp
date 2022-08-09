<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="search_wrapper" id="search_wrapper">
    <div class="search_box">
        <h1>비밀번호 찾기</h1>
        <p>이메일</p>
        <input type="text" name="email" id="email" placeholder="이메일을 입력해주세요.">
        <p class="error" id="email_error"></p>
        <br>
        <p>휴대폰</p>
        <input type="text" name="phone" id="phone" oninput="this.value = this.value.replaceAll(/\D/g, '')" placeholder="휴대폰 번호를 입력해주세요." maxlength="13">
        <p class="error" id="phone_error"></p>
    </div>
    <div class="search_btn_box">
        <input type="button" name="search_btn" id="search_btn" value="찾기">
    </div>
</div>
<div id="result_wrapper">
	<p id="result"></p>
	<a class="button" id="regist_button" href="/eatsorder/member/create_account">회원 가입</a>
</div>