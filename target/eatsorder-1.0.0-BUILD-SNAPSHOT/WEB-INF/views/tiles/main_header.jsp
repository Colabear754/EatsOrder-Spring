<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<c:url value="/resources/tiles/js/main_header_script.js"/>"></script>
<div class="header_top">
	<div class="logo">
		<a href="/eatsorder/main"><img src="<c:url value="/resources/tiles/img/Logo.png"/>" alt="로고-아이콘"></a>
	</div>
	<ul class="icon_menu">
		<c:if test="${eatsorder_uid == null}">
			<li><a href="/eatsorder/member/login"><i class="fa-solid fa-user fa"> Login</i></a></li>
		</c:if>
		<c:if test="${eatsorder_uid != null}">
			<li><label><i class="fa-solid fa-heart fa"> MyPage</i></label>
				<ul>
					<li><a href="/eatsorder/member/favorite_rst">찜 목록</a></li>
					<li><a href="/eatsorder/member/couponlist">쿠폰함</a></li>
					<li><a href="/eatsorder/member/reviewlist">리뷰관리</a></li>
					<li><a href="/eatsorder/member/orderlist">회원주문내역</a></li>
					<li><a href="/eatsorder/member/update_account">내 정보 수정</a></li>
				</ul></li>
			<li><a href="/eatsorder/member/cart" id="cart"><i class="fa-solid fa-cart-shopping fa"> Cart</i></a></li>
			<li><a href="/eatsorder/member/logout"><i class="fa-solid fa-power-off fa"> Logout</i></a></li>
		</c:if>
	</ul>
</div>