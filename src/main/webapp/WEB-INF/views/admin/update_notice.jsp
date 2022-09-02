<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<br>
<div class="outer-grid">
    <form method="post" name="writeform" enctype="multipart/form-data" action="/eatsorder/admin/update_process" onsubmit="return writeSave()">
        <input type="hidden" name="notice_number" value="${notice.notice_number}">
        <input type="hidden" name="filename" value="${notice.filename}">
        <input type="hidden" name="writer" value="${admin_id}">
        <table>
            <thead>
                <tr>
                    <th class="subject">공지사항/이벤트 글수정</th>
                </tr>
            </thead>    
            <tbody>              
                <tr>
                    <td class="td1 subject">말머리</td>
                </tr>
                <tr width="100%"> 
                    <td class="td2">
                        <select name="category">
                            <option value="1">[공지]</option>
                            <option value="2">[이벤트]</option>
                            <option value="3">[FAQ]</option>
                        </select>
                    </td>
                </tr>
                <tr><td class="td1 subject">제목</td></tr>
                <tr>
                    <td class="td2">
                        <input type="text" class="write_sub" name="title" placeholder="제목을 입력해주세요." value="${notice.title}">
                    </td>
                </tr>
                <tr><td class="td1 subject">내용</td></tr>
                <tr>
                    <td class="td2"><textarea class="write_cont" name="content">${notice.content}</textarea></td>
                </tr>
                <c:if test="${notice.filename != null}">
                    <tr><td class="td1 subject">기존 첨부 사진</td></tr>
                    <tr><td class="td2 center"><img src="/eatsorder/resources/notice/img/${notice.filename}"></td></tr>
                </c:if>
                <tr><td class="td1 subject">새로 첨부할 사진(기존 사진은 삭제됩니다.)</td></tr>
                <tr>
                    <td class="td1 td2"><input type="file" name="upload_file"></td>
                </tr>       
            </tbody>
        </table>
        <br>
        <div class="btn">
            <input type="submit" value="수정">
            <input type="button" value="이전으로" onclick="history.back()">
        </div>
    </form>
</div>   
