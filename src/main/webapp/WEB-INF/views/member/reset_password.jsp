<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="search_wrapper">
    <div class="search_box">
		<h1>비밀번호 재설정</h1>
        <h4>새 비밀번호</h4>
        <input type="hidden" id="email" value="${email}">
        <input type="password" name="password" id="password" placeholder="새 비밀번호를 입력해주세요">
        <div id="form_helper">
         	<p>10자 이상 입력</p>
         	<p>영문/숫자/특수문자 모두 조합</p>
         	<p>동일한 숫자 3개 이상 연속 사용 불가</p> 
       	  	<div class="error" id="password_error"></div>
       		<div class="available" id="available_password">사용 가능한 비밀번호입니다.</div>
        </div>
        <h4>새 비밀번호 확인</h4>
        <input type="password" name="re_pwd" id="re_pwd" placeholder="새 비밀번호를 다시 입력해주세요">
    	<div id="form_helper">
        	<p>비밀번호를 한번 더 입력해주세요</p>
        	<div class="error" id="re_pwd_error"></div>
        	<div class="available" id="re_pwd_equal">비밀번호가 일치합니다.</div>
        </div>
    </div>
    <div class="search_btn_box">
        <input type="button" name="submit" id="submit" value="재설정하기">
    </div>
</div>