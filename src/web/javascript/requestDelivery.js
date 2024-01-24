
var checker; //stops loading in nextpage if (EXCEPTION: Textfield == null)
var clickCount = 0; //find out which page it is at to execute functions
var offers;
var dataURL;

//init() 
$(document).ready(function () {

    var current_fs, next_fs, previous_fs; //fieldsets
    var opacity;
    var current = 1;
    var steps = $("fieldset").length;
    setProgressBar(current);

    $(".next").click(function () { 

        if(clickCount == 0){ //1st click
            checkNext(); // into 1 or 0
           
        }else if(clickCount == 1){ //2nd click
            checkNext2();

        }else if(clickCount == 2){ //3rd click
            checkNext3(); 
            //check if upload file is empty
            var fileInput = document.getElementById('pic').files.length;  
            if(fileInput!= 0){
                handleFileUpload();
            }

        }else if(clickCount == 3){ //4th click
            checkNext4(); 
            displayPrice();   
        }
        
            
        if (checker != 0) {
            current_fs = $(this).parent();
            next_fs = $(this).parent().next();

            //Add Class Active
            $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
            
            //show the next fieldset
            next_fs.show();
            
            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {

                step: function (now) {
                    // for making fielset appear animation
                    opacity = 1 - now;
                    current_fs.css({
                        'display': 'none',
                        'position': 'relative'
                    });
                    next_fs.css({'opacity': opacity});
                },
                duration: 500
            });
            setProgressBar(++current);

        }else{
            console.log("Null input");
        }
    });


    $(".previous").click(function () {
        
        current_fs = $(this).parent();
        previous_fs = $(this).parent().prev();
        
        if(clickCount!=0){
            clickCount--;
        }
        
        //Remove class active
        $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
        
        //show the previous fieldset
        previous_fs.show();
        
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
            step: function (now) {
                // for making fielset appear animation
                opacity = 1 - now;
                
                current_fs.css({
                    'display': 'none',
                    'position': 'relative'
                });
                
                previous_fs.css({'opacity': opacity});
            },
            duration: 500
        });
        setProgressBar(--current);
    });

    function setProgressBar(curStep) {
        var percent = parseFloat(100 / steps) * curStep;
        
        percent = percent.toFixed();
        $(".progress-bar").css("width", percent + "%")
    }
    
    $(".submit").click(function () {
        return false;
    })
});

//Check First Form
function checkNext() {
    // Get the input element
    var var1 = document.getElementById("recipient");
    var var2 = document.getElementById("r_phone");
    var var3 = document.getElementById("r_address");
    var var4 = document.getElementById("r_district");

    // Check if the value is null or empty
    if (var1.value == "" || var2.value == "" || var3.value == "" || var4.value == "") {
        alert("Please fill in all Text-Fields!");
        checker = 0;
        clickCount = 0;
    } else {
        checker = 1;
        clickCount = 1;
    }
}

//Check 2nd Form
function checkNext2() {
    // Get the input element
    var var1 = document.getElementById("name");
    var var2 = document.getElementById("phone");
    var var3 = document.getElementById("address");

    // Check if the value is null or empty
    if (var1.value == "" || var2.value == "" || var3.value == "") {
        alert("Please fill in all Text-Fields!");
        checker = 0;
        clickCount = 1;
    } else {
        checker = 1;
        clickCount = 2;
    }
}

//Check 3rd Form
function checkNext3() {
    // Get the input element
    var var1 = document.getElementById("packageType");
    var var2 = document.getElementById("packageWeight");
    var var3 = document.getElementById("packageQuantity");

    // Check if the value is null or empty
    if (var1.value == "" || var2.value == "" || var3.value == "") {
        alert("Please fill in all Text-Fields!");
        checker = 0;
        clickCount = 2;
    } else {
        checker = 1;
        clickCount = 3;
    }
}

//Check 3rd Form
function checkNext4() {
    // Get the input element
    var var1  = document.getElementById('standard');
    var var2 = document.getElementById('premium');
    var var3  = document.getElementById('noOffer');

    // Check if the value is null or empty
    if (var1.checked || var2.checked || var3.checked) {
        checker = 1;
        clickCount = 4;
    } else {
        alert("Please fill in all Text-Fields!");
        checker = 0;
        clickCount = 3;
    }
}

//Enable click select on OFFER page
function selectModel() {

    var firstOption = document.getElementById('standard');
    var secondOption = document.getElementById('premium');
    var thirdOption = document.getElementById('noOffer');
    var model1 = document.getElementsByClassName('price-col')[0];
    var model2 = document.getElementsByClassName('price-col2')[0];

    console.log("before events:");

    //Standard
    model1.addEventListener('click', function (event) {
        console.log("Clicked: 1st option " + firstOption.checked);
        firstOption.checked = true;
        secondOption.checked = false;
        thirdOption.checked = false;
        offers = document.querySelector('input[name="offer"]:checked').value;

    });

    //Premium
    model2.addEventListener('click', function (event) {
        console.log("Clicked: 2nd option " + secondOption.checked);      
        firstOption.checked  = false;
        secondOption.checked = true;
        thirdOption.checked  = false;
        offers = document.querySelector('input[name="offer"]:checked').value;
    });
    
    //No Offer
    thirdOption.addEventListener('click', function (event) {
        console.log("Clicked: 3rd option " + thirdOption.checked);      
        firstOption.checked  = false;
        secondOption.checked = false;
        thirdOption.checked  = true;
        offers = thirdOption.value;
    });

}

function displayPrice(){

    var express = document.getElementById('express');
    var fragile = document.getElementById('fragile');
    var expackage = document.getElementById('exPackage');
    var pweight = document.getElementById('packageWeight').value;

    var totalPrice = 0;
    
    if(express.checked){
        totalPrice += 3;
    }
    if(fragile.checked){
        totalPrice += 2;
    }
    if(expackage.checked){
        totalPrice += 2;
    }
    
    if(pweight == "lessthan1kg"){
        totalPrice += 4.9;
    }else if(pweight == "1kg-10kg"){
        totalPrice += 4.9;
    }else if(pweight == "11kg-30kg"){
        totalPrice += 9.9;
    }else if(pweight == "31kg-50kg"){
        totalPrice += 14.9;
    }else if(pweight == "morethan50kg"){
        totalPrice += 20;
    }else{
        console.log("ERROR: WEIGHT IS EMPTY!");
    }
    
    if(offers == "standard"){
        totalPrice += 4.9;
    }else if(offers == "premium"){
        totalPrice += 9.9;
    }
    
    var temp = document.getElementById('totalPrice');
    temp.innerHTML = "Total Price: RM" + totalPrice;
}

function sendData(){
    
    var rname = document.getElementById('recipient').value;
    var rphone = document.getElementById('r_phone').value;
    var raddress = document.getElementById('r_address').value;
    var name = document.getElementById('name').value;
    var rtown = document.getElementById('r_district').value;
    var phone = document.getElementById('phone').value;
    var address = document.getElementById('address').value;
    var ptype = document.getElementById('packageType').value;
    var pweight = document.getElementById('packageWeight').value;
    var pquantity = document.getElementById('packageQuantity').value;
    var express = document.getElementById('express').checked;
    var fragile = document.getElementById('fragile').checked;
    var expackage = document.getElementById('exPackage').checked;
    var off = document.querySelector('input[name="offer"]:checked').value;
    var amount = callPrice();
    
    sendServlet(rname,rphone,raddress,rtown,name,phone,address,ptype,pweight,pquantity,express,fragile,expackage,dataURL,off,amount);
    
}

function callPrice() {
    var inAmount = document.getElementById('totalPrice').innerHTML;
    var match = inAmount.match(/\d+\.\d+/);
    
    var returnAmount;
    // Extract the matched value as a floating-point number
    if (match) {
        returnAmount = parseFloat(match[0]);
        console.log(returnAmount);
    } else {
        console.log("No numeric value found in the string.");
    }
    return returnAmount;
}


function handleFileUpload() {
    var fileInput = document.getElementById('pic');
    var imagePreview = document.getElementById('prevPic');
    var file = fileInput.files[0];
    var reader = new FileReader();

    reader.onload = function (e) {
       imagePreview.src = e.target.result;
       dataURL = e.target.result;
       console.log('DataURL:', e.target.result);
       imagePreview.style.display = 'none';
    };
    reader.readAsDataURL(file);
}

function sendServlet(rname,rphone,raddress,rtown,name,phone,address,ptype,pweight,pquantity,express,fragile,expackage,dataURL,off,amount) {

    var jsonData = {
        rname: rname,
        rphone: rphone,
        raddress: raddress,
        rtown: rtown,
        name: name,
        phone: phone,
        address: address,
        ptype: ptype,
        pweight: pweight,
        pquantity: pquantity,
        express: express,
        fragile: fragile,
        expackage: expackage,
        dataURL: dataURL,
        offer: off,
        amount: amount
    };

    var jsonString = JSON.stringify(jsonData);

    // Create a new XMLHttpRequest object
    var xmlHR = new XMLHttpRequest();

    // Configure it: POST-request to the servlet
    xmlHR.open("POST", "RequestDeliveryServlet", true);

    // Set the request header
    xmlHR.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // Define what happens on successful data submission
    xmlHR.onload = function () {
        if (xmlHR.status == 200) {
            // Display the result from the servlet
            console.log("Data send successfully!");
        }
    };

    // Send the request with the data
    xmlHR.send("requestformData=" + jsonString);
}
