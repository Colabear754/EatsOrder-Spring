<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">
<div id="address-modal">
	<div id="wrapper"></div>
</div>
<input type="hidden" id="orderer" value="${eatsorder_uid}">
<input type="hidden" id="rst_id" value="${rst_id}">
<div class="tt col-sm-8">
	<div class="tt1">
		<ul>
			<li class="z1">결제하기</li>
			<li class="z2">배달정보</li>
		</ul>
		<div class="x1">
			주소<input type="text" class="address" id="address1" placeholder="배달 주소" value="${address}" readonly="readonly"><br>
		</div>
		<div class="x3">
			<input type="text3" class="address" id="address2" placeholder="(필수)상세주소 입력">
			<div class="address_error">주소를 입력하세요.</div>
		</div>
		<div class="x2">
			휴대전화번호<input type="text4" id="phone" name="phone" oninput="this.value = this.value.replaceAll(/\D/g, '')" placeholder="(필수)휴대전화 번호 입력" value="${phone}">
		</div>
	</div>

	<div class="tt2">
		<ul>
			<li class="z3">주문시 요청사항</li>
			<textarea class="text_t" rows="3" maxlength="100" id="order-request" placeholder="코로나19 예방을 위해 비대면 배달을 권장해 드립니다. 잇츠오더 결제 선택 후, '문 앞 배달'을 요청사항에 남겨주세요."></textarea>
			<div class="txt1">
				<span id="text-length">0</span>/100
			</div>
		</ul>
	</div>
	<div class="tt3">
		<ul>
			<li class="z4">결제수단 선택</li>
		</ul>
		<div class="r1">
			<label class="test_obj">
				<label class="test_obj1"> <input type="radio" name="c1" value="card"> <span>신용카드</span></label>
				<label class="test_obj2"> <input type="radio" name="c1" value="cash"> <span>현금</span></label>
				<label class="test_obj3"> <input type="radio" name="c1" value="naver"> <span>네이버페이</span></label>
				<label class="test_obj4"> <input type="radio" name="c1" value="smile"> <span>스마일페이</span></label>
			</label>
		</div>
	</div>
	<div class="tt4">
		<ul>
			<li class="z5">할인방법 선택</li>
			<li>
				쿠폰<input type="text1" id="coupon_id" placeholder="쿠폰 코드 입력"> <input type="button" value="적용" class="b1">
			</li>
			<li>
				<div id="able_point">사용 가능 포인트 : <span id="member_point"><fmt:formatNumber value="${point}" pattern="#,###" />P</span></div>
				포인트<input type="text2" id="point" oninput="this.value = this.value.replaceAll(/\D/g, '')" placeholder="포인트 입력"> <input type="button" id="use-point" value="적용" class="b1">
				<input type="hidden" id="using_point">
			</li>
		</ul>
	</div>
</div>
<div class="col-sm-4" id="cart-area"></div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>