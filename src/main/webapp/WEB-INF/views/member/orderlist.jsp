<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="orderCount" value="${fn:length(orderList)}" />
<div class="content_box">
	<div class="order_wrapper" id="order_wrapper2">
		<h1>주문내역</h1>
		<div class="grid_box2">
			<!-- 주문내역 박스1 -->

			<c:if test="${orderCount == 0}">
				<h3>주문내역이 없습니다.</h3>
			</c:if>
			<c:if test="${orderCount > 0}">
				<c:forEach var="order" items="${orderList}">
						<div class="order_box2">
							<h4>${order.ORDER_NUMBER}</h4>
							<h3>
								<a href="/eatsorder/restaurant/info?rst_id=${order.RST_ID}">${order.RST_NAME}<span>></span></a> <span class="order_status"> <c:choose>
										<c:when test="${order.PAYMENT_STATUS == 0}">주문 취소</c:when>
										<c:when test="${order.ELAPSED_TIME < (1 / 60) * 5}">주문 접수 대기</c:when>
										<c:when test="${order.ELAPSED_TIME >= (1 / 60) * 5 && order.ELAPSED_TIME < (1 / 60) * 15 }">메뉴 준비 중</c:when>
										<c:when test="${order.ELAPSED_TIME >= (1 / 60) * 15 && order.ELAPSED_TIME < (1 / 60) * 25}">배달 중</c:when>
										<c:otherwise>배달 완료</c:otherwise>
									</c:choose>
								</span>
							</h3>
							<c:if test="${order.ELAPSED_TIME < 1 && order.PAYMENT_STATUS != 0}">
								<div class="del_btn">
									<a href="/eatsorder/order/cancel_order?order_number=${order.ORDER_NUMBER}">주문 취소</a>
								</div>
							</c:if>
							<div class="order_img2">
								<img src='<c:url value="/resources/restaurant/img/${order.RST_LOGO}" />'>
							</div>
							<div class="order_text2">
								<p>${order.MENU_NAME}</p>
							</div>
						</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>