<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="content_box">
    <div class="coupon_wrapper">
        <h1>쿠폰함</h1>
        <div class="coupon_all_box">
	        <c:if test="${coupon_count == 0}">
	        	<h3>사용 가능한 쿠폰이 없습니다.</h3>
	        </c:if>
	        <c:if test="${coupon_count > 0}">
	        	<c:forEach var="coupon" items="${coupon_list}">
	            	<div class="coupon_box">
	                 	<div><img src="./img/coupon.png" alt="쿠폰-배경-이미지">
	                     	<p><fmt:formatNumber value="${coupon.discount_amount}" pattern="#,###" />원</p>
	                 	</div>
	                 	<h3><fmt:formatNumber value="${coupon.available_price}" pattern="#,###" />원 이상 주문 시</h3>
	                 	<p>${coupon.expiration_date}까지 사용</p>
	                 	<p>${coupon.coupon_info}</p>
	                 	<p>남은 사용 횟수 : ${coupon.available_count}</p>
	             	</div>
	            </c:forEach>
	        </c:if>
        </div>
    </div>
</div>