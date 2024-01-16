var amount = 0; //What will be the amount of the payment


paypal.Buttons({

    style : {
        color: 'blue',
        shape: 'pill'
    },
    
    onClick(){
        getPrice();
        
    },
    
    createOrder: function (data, actions) {

        return actions.order.create({
            purchase_units : [{
                amount: {
                    value: amount,      //SET STORE AMOUNT
                    currency: "MYR"
                },
                item_list:{
                      "country_code":"MY"
                }
            }]
        });
    },
    
    //Payment success
    onApprove: function (data, actions) {
        return actions.order.capture().then(function (details) {
            console.log(details)
            sendData(); 
            window.location.replace("CompletedServlet")
            //window.location.href = CompletedServlet;
            //redirectToServlet();
            
        })
    },
    //Payment failed
    onCancel: function (data) {
        window.location.replace("CancelledServlet")
    }
}).render('#paypal-button-container'); 



function getPrice() {
    var inAmount = document.getElementById('totalPrice').innerHTML;
    var match = inAmount.match(/\d+\.\d+/);

    // Extract the matched value as a floating-point number
    if (match) {
        amount = parseFloat(match[0]);
        console.log(amount);
    } else {
        console.log("No numeric value found in the string.");
    }
}
/*
function redirectToServlet(){
    
    var servletURL = 'RequestDeliveryServlet';

    var form = document.createElement('form');
    form.method = 'post';
    form.action = servletURL;

    document.body.appendChild(form);

    form.submit();
} */

