
//Preview Image from upload
function previewImage() {
    var fileInput = document.getElementById('avatar');
    var imagePreview = document.getElementById('imagePreview');

    var file = fileInput.files[0];
    var reader = new FileReader();

    reader.onload = function (e) {
        imagePreview.src = e.target.result;
        imagePreview.style.display = 'block';
    };

    reader.readAsDataURL(file);
}

//hide span
function hideImage(){

    var imagePlaceholder = document.getElementById('imagePlaceholder');
    
    imagePlaceholder.style.display = 'none';

}