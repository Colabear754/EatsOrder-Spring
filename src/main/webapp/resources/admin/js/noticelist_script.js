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
})