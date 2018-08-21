$(document).ready(function () {
    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();
    
    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);
    
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });
    
    $("#card-id-eff-date, #card-id-end-date, #dob").datepicker({
   	  defaultViewDate: {
   	    year: (new Date().getFullYear() + 543),
   	 	month: (new Date().getMonth()),
     	day: (new Date().getDate())
   	  },
   	  autoclose:true,
   	  format: 'dd/mm/yyyy'
   	});
    
    $('#card-id-eff-date, #card-id-end-date, #dob').datepicker('update', '17/08/2561');
    
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}

