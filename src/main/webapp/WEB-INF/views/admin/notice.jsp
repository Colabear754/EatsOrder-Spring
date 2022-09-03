<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="outer-grid">
	<br> <b style="text-align: center;">공지사항/이벤트</b> <br>
	<form>
		<input type="hidden" id="notice_number" value="${notice.notice_number}">
		<input type="hidden" id="pre_category" value="${pre_category}">
		<input type="hidden" name="category" value="${notice.category}">
		<table id="table">
			<tr>
				<td class="td1 subject">글번호</td>
				<td class="td2">${notice.notice_number}</td>
			</tr>

			<tr>
				<td class="td1 subject">말머리</td>
				<td class="td2" id="category">${notice.category}</td>
			</tr>

			<tr>
				<td class="td1 subject">작성일</td>
				<td class="td2">${notice.regist_date}</td>
			</tr>

			<tr>
				<td class="td1 subject">글제목</td>
				<td class="td2">${notice.title}</td>
			</tr>

			<tr>
				<td class="td1 subject">글내용</td>
				<td class="td2">
					<pre>${notice.content}</pre>
				</td>
			</tr>

			<tr>
				<c:if test="${notice.filename != null}">
					<tr>
						<td colspan="2" class="td2 center" style="text-align: center;">
							<img src="/eatsorder/resources/notice/img/${notice.filename}" alt="${notice.filename}">
						</td>
					</tr>
					<tr>
						<td class="td1 subject">첨부파일</td>
						<td class="td2">${notice.filename}</td>
					</tr>
				</c:if>
		</table>
		<br>
		<div class="btn">
			<input type="button" id="updatebtn" value="글수정"> &nbsp;&nbsp;&nbsp;&nbsp; <input type="button" id="deletebtn" value="글삭제"> &nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글목록" onclick="document.location.href='/eatsorder/admin/noticelist?&category=${pre_category}'">
		</div>
	</form>
</div>
