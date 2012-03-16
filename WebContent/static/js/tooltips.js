function show_tooltip(box_top, box_left, arrow_top, arrow_left, text) {
	$('.tooltip').html(text);
	$('.tooltip').css('top', box_top);
	$('.tooltip').css('left', box_left);
	$('.tooltip').css('visibility', 'visible');
	$('#triangle').css('top',arrow_top);
	$('#triangle').css('left',arrow_left);
	$('#triangle').css('visibility', 'visible');
}
function hide_tooltip() {
	$('.tooltip').css('visibility', 'hidden');
	$('#triangle').css('visibility', 'hidden');
}

$(document).ready(function() {
	
	$(".mini-image-with-tooltip").tipTip({maxWidth: "auto", delay: 0});

	// Binds hide_tooltip to all classes that have tooltips when you mouseout
	// ADD CLASS NAMES HERE when you bind a class with a tooltip
	$('.small-listing-image, .mini-image-with-tooltip, .listing-rating-box, .list-more, .feed-photo-list a img, .widget-image').bind('mouseout', function() {
		hide_tooltip();
	});


	// $('.listing-rating-box').bind('mouseover', function() {
	// 	var top = $(this).position().top + $(this).height();
	// 	var left = $(this).position().left;
	// 	show_tooltip(top, left, top, left+3, $(this).attr("data-type"))
	// 	$(this).css('cursor', 'default');
	// });

	$('.mini-image-with-tooltip').bind('mouseover', function() {
		var top = $(this).position().top + $(this).height()+2;
		var left = $(this).position().left;
		show_tooltip(top, left, top, left+3, $(this).attr("data-name"));
		$(this).css('cursor', 'pointer');
	});
});