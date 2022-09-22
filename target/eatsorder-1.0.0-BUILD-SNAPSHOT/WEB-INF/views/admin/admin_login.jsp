<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${admin_id != null}">
	<meta http-equiv="Refresh" content="0;url=/eatsorder/admin/main">
</c:if>
<div class="login_wrapper">
    <div class="login_box">
        <h1>관리자 로그인</h1>
        <input type="text" name="admin_id" id="admin_id" placeholder="관리자 계정을 입력해주세요">
        <input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요">
        <div id="login_error">계정 또는 비밀번호를 확인해주세요.</div>
        <div class="login_btn_box">
            <input type="button" name="login" id="login" value="로그인">
        </div>
    </div>
</div>