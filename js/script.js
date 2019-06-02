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

function toggleStudents(element){
    //remove from list onclick
    element.onclick = function() {
        var parent = element.parentNode.id;

        if (parent == 'allStudents'){
            var classes = document.getElementById('classes');
            this.parentNode.removeChild(this);
            classes.appendChild(this);
            parent = '';
        }
        else if(parent == 'classes'){
            var allStudents = document.getElementById('allStudents');
    
            element.onclick = function() {
                this.parentNode.removeChild(this);
                allStudents.appendChild(this);
                parent = '';
            }
        }
    }
}

// function removeFromClass(element){
//     var allStudents = document.getElementById('allStudents');
//     element.onclick = function() {
//         this.parentNode.removeChild(this);
//         allStudents.appendChild(this);
//     }
//     // var classes = document.getElementById('classes');
//     // var listItems = classes.getElementsByTagName("a"); 
//     // var allStudents = document.getElementById('allStudents');
//     // for (var i = 0; i < listItems.length; i++) {
//     //   listItems[i].onclick = function() {
//     //       this.parentNode.removeChild(this);
//     //       allStudents.appendChild(this);
//     //     }
//     // }

// }
// var imgBtn = document.getElementsByClassName('profilePicture');
// var fileInp = document.querySelector('[type="file"]');

// // imgBtn.addEventListener('click', function() {
// //   fileInp.click();
// // })
