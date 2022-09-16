<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><tiles:getAsString name="title" /></title>
	<style>
		@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="${contextpath}<tiles:getAsString name="header_style" />">
	<link rel="stylesheet" href="${contextpath}<tiles:getAsString name="mypage_style" />">
	<link rel="stylesheet" href="${contextpath}<tiles:getAsString name="css" />">
	<link rel="stylesheet" href="${contextpath}<tiles:getAsString name="footer_style" />">
	<script type="text/javascript" src='${contextpath}<tiles:getAsString name="js" />'></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://kit.fontawesome.com/6cc0f3db28.js" crossorigin="anonymous"></script>
    <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body style="margin: 0;">
	<header>
		<tiles:insertAttribute name="header" />
	</header>
	
	<main>
		<tiles:insertAttribute name="mypage_side" />
		<tiles:insertAttribute name="body" />
	</main>

	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>