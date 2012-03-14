function crop_image(image) {
    var w = $(image).width();
	var h = $(image).height();
	var max_width = $(image).attr('data-width');
	var max_height = $(image).attr('data-height');
	if (h < w) {
	    $(image).css('width','auto');
	    $(image).css('height', max_height + 'px');
	} else {
	    $(image).css('width', max_width + 'px');
	    $(image).css('height','auto');
	}
}

/* Initializes the dropdown menu on the header. We want to open the menu
 * when the user clicks "#session" and close the menu when the user clicks
 * anywhere outside '#session. In order to do this, we attach a click 
 * event to the document body which closes the nav dropdown. Then we attach
 * a separate click event to the nav dropdown button which opens the nav 
 * dropdown and stops propagation to the document body (so that the html 
 * click isn't triggered).
 */
function init_dropdown() {
    $('.dropdown').hide();
    
    $('html').click(function() {
        $('.dropdown').hide();
        $("#nav-account").removeClass("user-dropdown-on");
        $('.arrow-down').removeClass('arrow-down-selected').addClass('arrow-down-unselected');
    });

    $('#session').click(function(event){
        event.stopPropagation();
        if($(".dropdown").is(":visible")) {
            $(".dropdown").hide();
            $("#nav-account").removeClass("user-dropdown-on");
            $('.arrow-down').removeClass('arrow-down-selected').addClass('arrow-down-unselected');
        } else {
            $(".dropdown").show();
            $("#nav-account").addClass("user-dropdown-on");
            $('.arrow-down').removeClass('arrow-down-unselected').addClass('arrow-down-selected');
        }
    });
    
}

/// js for search bar
$(document).ready(function() {
	
    // Images won't always trigger 'load'
    // See the Caveats of 'load' at http://api.jquery.com/load-event/
    $(".cropped-image").load(function() {
        crop_image(this);
    });
    
    // Crops all images with the class 'cropped-image'
    // Does this, so when the image doesn't trigger the 'load' action, it still gets cropped
    $(".cropped-image").each(function() {
        crop_image(this);
    });
	
    init_dropdown();
});