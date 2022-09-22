<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<div class="outer-grid">
    <form method="post" name="writeform" enctype="multipart/form-data" action="/eatsorder/admin/write_process">
        <input type="hidden" name="writer" value="${admin_id}">
        <table>
            <thead>
                <tr>
                    <th class="subject">공지사항/이벤트 작성</th>
                </tr>
            </thead>    
            <tbody>              
                <tr><td class="td1 subject">말머리</td></tr>
                <tr width="100%"> 
                    <td class="td2">
                        <select name="category">
                            <option value="1" selected>[공지]</option>
                            <option value="2">[이벤트]</option>
                            <option value="3">[FAQ]</option>
                        </select>
                    </td>
                </tr>
                <tr><td class="td1 subject">제목</td></tr>
                <tr>
                    <td class="td2"><input type="text" class="write_sub" name="title" placeholder="제목을 입력해주세요."></td>
                </tr>
                <tr><td class="td1 subject">작성자</td></tr>
				<tr><td><input type="text" value="${admin_id}" disabled="disabled"></td></tr>
                <tr><td class="td1 subject">내용</td></tr>
                <tr>
                    <td class="td2"><textarea class="write_cont" name="content"></textarea>
                </tr>
                <tr><td class="td1 subject">첨부할 사진(20MB까지 첨부 가능)</td></tr>
                <tr>
                    <td class="td1 td2"><input type="file" name="upload_file" multiple="multiple"></td>
                </tr>
            </tbody>
        </table>
        <br>
        <div class="btn">
            <input  type="submit" value="글등록하기">
            <input  type="reset" value="다시 쓰기">
            <input  type="button"value="목록보기" OnClick="window.location='/eatsorder/admin/noticelist?category=${category}'">
        </div>
    </form>
</div>  