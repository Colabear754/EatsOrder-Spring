/* 
작성자: 김나연
작성완료일: 22/04/04
페이지명: 로그인

수정자 : 정건영
수정일 : 2022/04/10
수정내용 : ajax로그인 추가
*/

$(document).ready(function(){

    $("form").submit(function(){
        //이메일 공백 확인
        if($("#account").val() == ""){
          alert("이메일을 입력해주세요");
          $("#account").focus();
          return false;
        }
  
        //비밀번호 공백 확인
        if($("#password").val() == ""){
          alert("비밀번호를 입력해주세요");
          $("#password").focus();
          return false;
        }
    });
    
    // 로그인버튼으로 로그인
    $("#login").click(function() {
    	var account = $("#account").val()
    	var password = $("#password").val()
    	var preUrl = document.referrer
    	$.ajax({
    		type: "POST",
    		url: "/eatsorder/member/loginCheck",
    		data: {
    			"email": account, 
    			"password": password,
    		},
    		success: function(data) {
    			if (data.indexOf("true") > 0) {
    				window.location.href = preUrl
    			} else {
    				$("#login_error").css("display", "block")
    				$("#account").css("outline", "2px solid red")
    				$("#password").css("outline", "2px solid red")
    			}
    		},
    		error: function(request) {
    			alert("오류 발생 : " + request.status)
    		}
    	})
    })
    
    // 엔터키를 눌렀을 때 로그인
    $('#account').keyup(function(e) {
		$(this).css('outline', '')
		if (e.keyCode == 13) {
			login()
		}
	})
	
	$('#password').keyup(function(e) {
		$(this).css('outline', '')
		if (e.keyCode == 13) {
			login()
		}
	})
});

//엔터키를 눌렀을 때 로그인 되도록 별도 함수로 작성
function login() {
	var account = $("#account").val()
	var password = $("#password").val()
	var preUrl = document.referrer
	$.ajax({
		type: "POST",
		url: "/eatsorder/member/loginCheck",
		data: {
			"email": account, 
			"password": password,
		},
		success: function(data) {
			if (data.indexOf("true") > 0) {
				if (preUrl.indexOf("/member/") > 0) {
					window.location.href = "/eatsorder/main"
				} else {
					window.location.href = preUrl
				}
			} else {
				$("#login_error").css("display", "block")
				$("#account").css("outline", "2px solid red")
				$("#password").css("outline", "2px solid red")
			}
		},
		error: function(request) {
			alert("오류 발생 : " + request.status)
		}
	})
}