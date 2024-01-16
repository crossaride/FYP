var barcodeID;

function generateCode() {

    JsBarcode("#barcode", "123456789", {
        format: "CODE128", // Choose the barcode format
        width: 2,
        height: 100,
        displayValue: true
    });

}

    // Function to save as PNG
    function saveAsPNG() {
        barcodeID = document.querySelectorAll("#barcode");
        
        html2canvas(barcodeID).then(function(canvas) {
            var imgData = canvas.toDataURL('image/png');
            var link = document.createElement('a');

            link.href = imgData;
            link.download = 'barcode.png';
            link.click();
        });
    }
    
     function generateQRCode() {
        const textToEncode = document.getElementById("textToEncode").value; // Get text from an input element

        if (textToEncode.trim() !== "") {
            // Create a QR code instance
            const qrcode = new QRCode(document.getElementById("qrcode"), {
                text: textToEncode + " " + " say",
                width: 128,
                height: 128
            });
        } else {
            alert("Please enter text to generate a QR code.");
        }
    }
    
    function saveAsPNG2(image) {

        html2canvas(image).then(function(canvas) {
            var imgData = canvas.toDataURL('image/png');
            var link = document.createElement('a');

            link.href = imgData;
            link.download = 'qr.png';
            link.click();
        });
    }