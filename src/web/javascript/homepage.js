var navbar;

// Get the navbar element in function because it won't load/read anything outside a function
function getNav(){
    navbar = document.getElementById("navb");
}

// Function to change the background color on scroll
function changeNavbarColor() {
    
    if (window.scrollY > 50) {
        navbar.style.backgroundColor = "Black"; // Change to the desired color
    } else {
        navbar.style.backgroundColor = "Transparent"; // Reset to the initial color
    }
}

//Get nav ID onload 
window.onload = getNav;

// Attach the function to the scroll event
window.addEventListener("scroll", changeNavbarColor);