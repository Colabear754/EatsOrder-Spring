<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="url" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<c:if test="${admin_id == null && !fn:contains(url, 'login')}">
	<meta http-equiv="Refresh" content="0;url=/eatsorder/main">
</c:if>
<div class="header_top">
    <div class="header_box">
        <a href="/eatsorder/admin/main" class="logo"><img id="logo_img" src="/eatsorder/resources/member/img/Logo_white.png" alt="로고-아이콘"></a>
        <ul class="icon_menu">
        	<c:if test="${admin_id != null}">
				<li><a href="/eatsorder/admin/logout"><i class="fa-solid fa-power-off fa"> Logout</i></a></li>
			</c:if>
		</ul>
    </div>
</div>