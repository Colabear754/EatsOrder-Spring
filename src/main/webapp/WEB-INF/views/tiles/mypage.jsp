<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${eatsorder_uid == null}">
	<meta http-equiv="Refresh" content="0;url=/eatsorder/main">
</c:if>
<h2 class="mypage_head">마이페이지</h2>
<div class="mypage_wrap">
	<div class="profile_box">
		<div class="membership_lv">
			<div>
				<strong>${member.membership}</strong>
			</div>
		</div>
		<p>안녕하세요, ${member.nickname}님</p>
		<ul>
			<li><a href="/eatsorder/member/couponlist"><strong>${coupon_count}장</strong><br>쿠폰</a></li>
			<li><a><strong>${member.point}P</strong><br>포인트</a></li>
			<li><a href="/eatsorder/member/favorite_rst"><strong>찜 매장</strong></a></li>
			<li><a href="/eatsorder/member/orderlist"><strong>주문내역</strong></a></li>
		</ul>
	</div>
	<div class="quick_menu" id="quick_menu">
		<div>
			<h3>quick menu</h3>
		</div>
		<ul>
			<li><a href="/eatsorder/member/orderlist">회원주문내역</a></li>
			<li><a href="/eatsorder/member/favorite_rst">찜 매장</a></li>
			<li><a href="/eatsorder/member/couponlist">쿠폰함</a></li>
			<li><a href="/eatsorder/member/reviewlist">리뷰관리</a></li>
			<li><a href="/eatsorder/member/update_account">내 정보 수정</a></li>
		</ul>
	</div>
</div>