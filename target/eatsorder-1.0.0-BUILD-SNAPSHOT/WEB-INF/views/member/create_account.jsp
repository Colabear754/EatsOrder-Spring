<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="all_box">
	<div id="terms-modal">
		<div id="wrapper"></div>
	</div>
	<div class="title_box">
		<h1>회원가입</h1>
		<hr>
	</div>
	<div class="join_form">
		<table>
			<tbody>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="id_input" id="email" placeholder="이메일을 입력해주세요."> <a class="duplicate_check" id="email_check">중복확인</a>
					</td>
				</tr>
				<tr id="form_helper1" class="form_helper">
					<th></th>
					<td>
						<p>예시)food123@eatsorder.com</p>
						<div class="error" id="email_error"></div>
						<div class="available" id="available_email">사용 가능한 이메일입니다.</div>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="id_input" id="password" placeholder="비밀번호를 입력해주세요.">
					</td>
				</tr>
				<tr id="form_helper2" class="form_helper">
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
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="id_input" id="re_pwd" placeholder="비밀번호를 입력해주세요.">
					</td>
				</tr>
				<tr id="form_helper3" class="form_helper">
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
						<input type="text" name="id_input" id="nickname" placeholder="닉네임을 입력해주세요."> <a class="duplicate_check" id="nickname_check">중복확인</a>
					</td>
				</tr>
				<tr id="form_helper4" class="form_helper">
					<th></th>
					<td>
						<p id="nickname_notice">한글, 영어, 숫자만 사용</p>
						<div class="error" id="nickname_error"></div>
						<div class="available" id="available_nickname">사용 가능한 닉네임입니다.</div>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>
						<input type="text" name="id_input" id="phone" oninput="this.value = this.value.replaceAll(/\D/g, '')" maxlength="13"> <a class="duplicate_check" id="phone_check">중복확인</a>
					</td>
				</tr>
				<tr id="form_helper5" class="form_helper">
					<th></th>
					<td>
						<p id="phone_notice">숫자만 입력하세요.</p>
						<div class="error" id="phone_error"></div>
						<div class="available" id="available_phone">사용 가능한 휴대폰 번호입니다.</div>
					</td>
				</tr>
				<tr>
					<th>이용약관 동의</th>
					<td class="check_box">
						<div>
							<label id="terms_label"> <input type="checkbox" id="agree"> <span>이용약관 동의(필수)</span></label> 
							<a id="terms">약관보기></a>
						</div>
					</td>
				</tr>
				<tr>
					<th></th>
					<td class="check_box">
						<div>
							<label> <input type="checkbox" id="receive_marketing"> <span>마케팅 정보 수신동의(선택)</span></label>
						</div>
					</td>
				</tr>
				<tr class="form_helper">
					<th></th>
					<td>
						<div class="error" id="terms_error">이용약관에 동의해주세요.</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="join_box">
			<hr>
			<button class="button" id="submit">가입하기</button>
			<button class="button" onclick="history.back()">취소</button>
		</div>
	</div>
</div>