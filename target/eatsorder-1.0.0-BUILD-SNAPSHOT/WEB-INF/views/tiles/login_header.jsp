<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="uri" value="${pageContext.request.requestURI}" />
<c:if test="${eatsorder_uid == null} && ${!fn:contains(uri, 'login')}">
	<meta http-equiv="Refresh" content="0;url=/eatsorder/main">
</c:if>
<div class="header_top">
	<div class="header_box">
		<a href="/eatsorder/main" class="logo"><img src="/eatsorder/resources/member/img/Logo_white.png" alt="로고-아이콘"></a>
		<ul class="icon_menu">
			<li><a href="/eatsorder/member/login"><i class="fa-solid fa-user"> Login</i></a></li>
		</ul>
	</div>
</div>