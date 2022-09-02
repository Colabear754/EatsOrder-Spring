$(function() {
	$('#deletebtn').click(function(event) {
		event.preventDefault()
		
		var notice_number = $('#notice_number').val()
		var category = $('#category').val()
		
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
					url: "/eatsorder/order/delete_notice",
					data: "notice_number=" + notice_number,
					success: function(data) {
						if (data > 0) {
							swal("공지가 삭제되었습니다.", "목록으로 돌아갑니다.", "success").then((a) => {
		    					if (a) {
		    						window.location.href = "/eatsorder/admin/noticelist?category=" + category
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