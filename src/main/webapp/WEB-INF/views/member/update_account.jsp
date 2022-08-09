<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${eatsorder_uid == null}">
	<meta http-equiv="Refresh" content="0;url=/eatsorder/main"> 
</c:if>
<div class="all_box">
	<div class="title_box">
		<h1>회원정보수정</h1>
		<hr>
	</div>
	<table>
		<tbody>
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" name="email" value="${member.email}" disabled="disabled" />
					<input type="hidden" name="email" id="email" value="${member.email}">
				</td>
			</tr>
			<tr>
				<th>현재 비밀번호</th>
				<td>
					<input type="password" name="password" id="password" placeholder="현재 비밀번호를 입력해주세요." />
				</td>
			</tr>
			<tr class="form_helper">
				<th></th>
				<td>
					<div class="error" id="ex_password_error"></div>
				</td>
			</tr>
			<tr>
				<th>새 비밀번호</th>
				<td>
					<input type="password" name="newPassword" id="newPassword" placeholder="새 비밀번호를 입력해주세요.">
				</td>
			</tr>
			<tr id="form_helper1" class="form_helper">
				<th></th>
				<td>
					<p>10자 이상 입력</p>
					<p>영문/숫자/특수문자 모두 조합</p>
					<p>동일한 숫자 3개 이상 연속 사용 불가</p>
					<div class="error" id="password_error"></div>
					<div class="available" id="available_password">사용 가능한 비밀번호입니다.</div>
				</td>
			</tr>
			<tr>
				<th>새 비밀번호 확인</th>
				<td>
					<input type="password" name="renew_pwd" id="renew_pwd" placeholder="새 비밀번호를 한번 더 입력해주세요.">
				</td>
			</tr>
			<tr id="form_helper2" class="form_helper">
				<th></th>
				<td>
					<p>비밀번호를 한번 더 입력해주세요</p>
					<div class="error" id="re_pwd_error"></div>
					<div class="available" id="re_pwd_equal">비밀번호가 일치합니다.</div>
				</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>
					<input type="text" name="nickname" id="nickname" value="${member.nickname}" placeholder="닉네임을 입력해주세요.">
					<input type="hidden" id="nickname2" value="${member.nickname}">
					<a class="duplicate_check" id="nickname_check">중복확인</a>
				</td>
			</tr>
			<tr id="form_helper3" class="form_helper">
				<th></th>
				<td>
					<p>한글, 영어, 숫자만 사용</p>
					<div class="error" id="nickname_error"></div>
					<div class="available" id="available_nickname">사용 가능한 닉네임입니다.</div>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>
					<input type="text" name="phone" id="phone" value="${member.phone}" oninput="this.value = this.value.replaceAll(/\D/g, '')" placeholder="휴대폰 번호를 입력하세요." maxlength="13">
					<input type="hidden" id="phone2" value="${member.phone}">
					<a class="duplicate_check" id="phone_check">중복확인</a>
				</td>
			</tr>
			<tr id="form_helper4" class="form_helper">
				<th></th>
				<td>
					<p>숫자만 입력하세요</p>
					<div class="error" id="phone_error"></div>
					<div class="available" id="available_phone">사용 가능한 휴대폰 번호입니다.</div>
				</td>
			</tr>
			<tr>
				<th>이용약관 동의</th>
				<td class="check_box">
					<label> 
						<input type="checkbox" name="receive_marketing"> 
						<span>마케팅 정보 수신동의</span>
					</label>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="member_box">
		<input type="button" value="수정 완료" id="submit"> <a href="javascript:history.back()">취소</a> <a href="/eatsorder/member/delete_account">탈퇴하기</a>
	</div>
</div>