<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="container">
    <ul class="tabs">
        <li class="tab1 tabmenu" data-tab="tab1" id="default" value="1"><a class="tab11" href="/eatsorder/notice/noticelist?category=1">공지사항</a></li>
        <li class="tab2 tabmenu" data-tab="tab2" value="2"><a href="/eatsorder/notice/noticelist?category=2">이벤트</a></li>
        <li class="tab3 tabmenu" data-tab="tab3" value="3"><a href="/eatsorder/notice/noticelist?category=3">FAQ</a></li>
    </ul>
    <br>
    <div class="search">
        <form name="test" action="/eatsorder/notice/noticelist">
            <select name="search" >
                <option value="title">제목</option>
                <option value="content">본문</option>
                <option value="title_content">제목+본문</option>
            </select>
            <input class="search2" type="text" name="searchtext">
            <input type="hidden" name="category" value="${category}">
            <input class="search_btn"  type="submit" value="검색">
            <br><br>
        </form>
    </div>
    
    <br><br>
    <b class="board_count">총 ${count}건 [ ${currentPage} / ${pageCount} ]</b>
    <br><br>
    
    <div class="outer-grid">
    	<input type="hidden" id="category" value="${category}">
        <c:if test="${count==0}"> 
            <table>
                <tr>
                    <td id="empty"></td>
                </tr>
            </table>
        </c:if>
        <c:if test="${count > 0}">
            <table>
                <tr>
                    <th class="td1">글번호</th>
                    <th class="td2">글제목</th>
                    <th>등록일</th>
                </tr>
                <c:set var="number" value="${number}"/>
                <c:forEach var="notice" items="${noticeList}">
                    <tr>
                        <td class="td1">
                            <c:out value="${notice.notice_number}"/>
                            <c:set var="notice_number" value="${notice.notice_number-1}"/>
                        </td>
            
                        <td class="td2">
                            <a class="title" href="/eatsorder/notice/notice?notice_number=${notice.notice_number}&pageNum=${currentPage}&category=${category}">${notice.title}</a> 
                        </td>
                        <td>
                            <fmt:formatDate value="${notice.regist_date}" timeStyle="medium" pattern="yy.MM.dd" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>           
    </div>
	
	<div class="numbering">
        <c:if test="${startPage > blockSize}">
			<a href="/eatsorder/notice/noticelist?pageNum=${startPage - blockSize}&search=${search}&searchtext=${searchtext}&category=${category}">[이전]</a>
		</c:if>
        <c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/eatsorder/notice/noticelist?pageNum=${i}&search=${search}&searchtext=${searchtext}&category=${category}">
						<c:if test="${currentPage == i}">
                            <b style="color: red;">[${i}]</b>
						</c:if>
						<c:if test="${currentPage != i}">
                            ${i}
						</c:if>
					</a>
		</c:forEach>
	
        <c:if test="${endPage < pageCount}">
            <a href="/eatsorder/notice/noticelist?pageNum=${startPage + blockSize}&search=${search}&searchtext=${searchtext}&category=${category}">[다음]</a> 
		</c:if>
	</div>
</div>