var $checky = $("input[type=checkbox].checky");
    $checky.click(function() {
    //calculateTotal();
    if ($(this).is(":checked")) {
        calculateTotal();

    } else {
        var index = $(this).closest("tr").attr("data-index");
        calculateTotal();
    }
});

function calculateTotal() {
//if($(this).is(":checked")) {
    var total = 0;

    $checky.filter(":checked").each(function() {
        total = parseFloat(total) + parseFloat($(this).val());
    });
    $("#total").text(total);
    
}

function hideLabel(status){
    document.getElementById('pickAnOptionLabel').style.display= status;
}


function moveOnClick(element){
    var allStudents = document.getElementById('allStudents');
    element.remove();

}
// var imgBtn = document.getElementsByClassName('profilePicture');
// var fileInp = document.querySelector('[type="file"]');

// // imgBtn.addEventListener('click', function() {
// //   fileInp.click();
// // })
