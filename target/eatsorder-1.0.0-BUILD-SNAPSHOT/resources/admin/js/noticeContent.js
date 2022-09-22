$(function(){
	var num=$("[name='category']").val();
	
	switch (num){
		case '1':
			$("#category").text('공지사항');
			break;
		case '2':
			$("#category").text('이벤트');
			break;
		case '3':
			$("#category").text('FAQ');
			break;			
	}
	
	$('#updatebtn').click(function(event) {
		event.preventDefault()
		
		var newForm = $('<form></form>')
		newForm.attr('method', 'post')
		newForm.attr('action', '/eatsorder/admin/update_notice')
		// 폼에 항목 추가
		newForm.append($('<input>', {type: 'hidden', name: 'notice_number', value: $('#notice_number').val()}))
		newForm.append($('<input>', {type: 'hidden', name: 'pre_category', value: $('#category').val()}))
		// 폼을 body에 추가
		newForm.appendTo('body')
		
		newForm.submit()
	})
		
	$('#deletebtn').click(function(event) {
		event.preventDefault()
		
		var notice_number = $('#notice_number').val()
		var pre_category = $('#pre_category').val()
		
		swal({
			title: "공지를 삭제하시겠습니까?",
			icon: "warning",
			buttons: [
				'아니오', '예'
			]
		}).then((result) => {
			if (result) {
				$.ajax({
					type: "POST",
					url: "/eatsorder/admin/delete_notice",
					data: "notice_number=" + notice_number,
					success: function(data) {
						if (data > 0) {
							swal("공지가 삭제되었습니다.", "목록으로 돌아갑니다.", "success").then((a) => {
		    					if (a) {
		    						window.location.href = "/eatsorder/admin/noticelist?category=" + pre_category
		    					}
		    				});
						}
					},
					error: function(request) {
						alert('오류 발생 : ' + request.statusText)
					}
				})
			}
		})
	})
})