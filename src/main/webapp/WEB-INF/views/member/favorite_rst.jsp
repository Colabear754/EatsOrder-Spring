<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="favoriteRstCount" value="${fn:length(favorite_rst)}" />
<div class="content_box">
    <div class="like_wrapper" id="like_wrapper2">
        <h1>찜 목록</h1>
        <c:if test="${favoriteRstCount == 0}">
        	<h3>찜 매장이 없습니다.</h3>
        </c:if>
        <c:if test="${favoriteRstCount > 0}">
        	<div class="grid_box2">
        	<c:forEach var="favoriteRst" items="${favorite_rst}">
              	<div class="like_box2">
                  	<h3><a href="/eatsorder/restaurant/rst?rst_id=${favoriteRst.restaurant.rst_id}">${favoriteRst.restaurant.rst_name}<span>></span></a></h3>
                  	<div class="del_btn">
                      	<a href="#">삭제</a>
                  	</div>
                  	<div class="like_img2">
                      	<img src="<c:url value="/resources/restaurant/img/${favoriteRst.restaurant.rst_logo}" />" width="100px" height="100px">
                  	</div>
                  	<div class="like_text2">
                      	<span>★</span><span>${favoriteRst.rating}</span>
                      	<p>최소주문 <fmt:formatNumber value="${favoriteRst.restaurant.min_order}" pattern="#,###" />원</p>
                  	</div>
              	</div>
         	</c:forEach>
          	</div>
        </c:if>
    </div>
</div>