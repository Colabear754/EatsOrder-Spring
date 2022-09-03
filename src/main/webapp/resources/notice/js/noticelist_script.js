/**
 * 
 */
$(function() {
	$('.tabmenu').hover(function() {
		$(this).addClass('hover');
	}, function() {
		$(this).removeClass('hover');
	})
	
	$('.title').hover(function() {
		$(this).addClass('title_hover')
	}, function() {
		$(this).removeClass('title_hover')
	})
	
	var category = $('#category').val()
	
	switch (Number(category)) {
	case 1:
		$('#empty').text('작성된 공지사항이 없습니다.')
		break;
	case 2:
		$('#empty').text('진행중인 이벤트가 없습니다.')
		break;
	case 3:
		$('#empty').text('등록된 FAQ가 없습니다.')
		break;
	}
})