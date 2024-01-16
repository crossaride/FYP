//Load data, GET from servlet
function populateTable() {
    var xmlHR = new XMLHttpRequest();

    // Set up the request to the Servlet
    xmlHR.open("GET", 'http://localhost:8080/FYP/Acc_LoadServlet', true);

    // Set the callback function to handle the response
    xmlHR.onreadystatechange = function () {
        if (xmlHR.readyState === 4 && xmlHR.status === 200) {

            // Parse the JSON response
            var jsonData = JSON.parse(xmlHR.responseText);

            // Populate the table with JSON data
            var tableBody = document.getElementById("table-body");
            jsonData.forEach(function (obj) {
                var row = tableBody.insertRow();

                row.insertCell(0).textContent = obj.id;
                row.insertCell(1).textContent = obj.email;
                row.insertCell(2).textContent = obj.username;
                row.insertCell(3).textContent = obj.password;
                row.insertCell(4).textContent = obj.accType;

                // Add button to row
                var buttonCell = row.insertCell(5);

                var button = document.createElement("button");
                var button2 = document.createElement("button");
                
                button2.setAttribute("class","sourceText");
                button2.innerHTML ="Delete <i class='fa fa-trash'>";
                
                button.textContent = "Edit";
                //button2.textContent = "Delete";
                
                button.style.color="white";
                button.style.backgroundColor ="blue";
                button2.style.color="red";  

                button.onclick = function () {
                    editData(button);
                };

                button2.onclick = function () {
                    deleteData(button);
                };

                buttonCell.appendChild(button);
                buttonCell.appendChild(button2);
            });

        }
    };
    xmlHR.send();
}

//Display form
function openForm() {
    document.getElementById("formContainer").style.display = "block";
}

//Close form
function closeForm() {
    document.getElementById("formContainer").style.display = "none";
}

//Reset input
function clearInputs() {
    document.getElementById("emailInput").value = "";
    document.getElementById("usernameInput").value = "";
    document.getElementById("passwordInput").value = "";
    document.getElementById("accTypeInput").value = "";
}

//Add button
function addData() {
    // Get input values 
    let email = document.getElementById("emailInput").value;
    let username = document.getElementById("usernameInput").value;
    let password = document.getElementById("passwordInput").value;
    let accType = document.getElementById("accTypeInput").value;

    // Get a reference to the table
    var table = document.getElementById("table-body");
    
    
     // Get the last row index for 'ID'
    var lastRowIndex = table.rows.length - 1;
    var lastRow      = table.rows[lastRowIndex];
    var lastRowData  = lastRow.cells[0].innerHTML;
    let cellData     = parseInt(lastRowData);

    // Create a new row
    var newRow = table.insertRow();

    // Insert cells into the new row
    var cell0 = newRow.insertCell(0);
    var cell1 = newRow.insertCell(1);
    var cell2 = newRow.insertCell(2);
    var cell3 = newRow.insertCell(3);
    var cell4 = newRow.insertCell(4);
    var cell5 = newRow.insertCell(5);

    // Set the content of the cells
    cell0.innerHTML = cellData+1;
    cell1.innerHTML = email;
    cell2.innerHTML = username;
    cell3.innerHTML = password;
    cell4.innerHTML = accType;
    cell5.innerHTML = '<button onclick="editData(this)">Edit</button>' +
            '<button onclick="deleteData(this)">Delete</button>';

    sendToServlet3(email, username, password, accType);
    
    // Clear input fields 
    clearInputs();
}

//Edit button
function editData(button) {

    // Get the parent row of the clicked button 
    let row = button.parentNode.parentNode;

    // Get the cells within the row 
    let idCell = row.cells[0];
    let emailCell = row.cells[1];
    let usernameCell = row.cells[2];
    let passwordCell = row.cells[3];
    let accTypeCell = row.cells[4];

    // Prompt the user to enter updated values  
    let emailInput = prompt("Enter new Email Address:", emailCell.innerHTML);
    let usernameInput = prompt("Enter new username:", usernameCell.innerHTML);
    let passwordInput = prompt("Enter new password:", passwordCell.innerHTML);
    let accTypeInput = prompt("Enter the updated Account Type:", accTypeCell.innerHTML);

    // Update the cell contents with the new values 
    emailCell.innerHTML = emailInput;
    usernameCell.innerHTML = usernameInput;
    passwordCell.innerHTML = passwordInput;
    accTypeCell.innerHTML = accTypeInput;

    sendToServlet(idCell.innerHTML, emailCell.innerHTML, usernameCell.innerHTML,
            passwordCell.innerHTML, accTypeCell.innerHTML);
}

//Delete button        
function deleteData(button) {

    // Get the parent row of the clicked button 
    let row = button.parentNode.parentNode;

    // Get the cells within the row 
    let idCell = row.cells[0];

    // Remove the row from the table 
    row.parentNode.removeChild(row);

    sendToServlet2(idCell.innerHTML);
}


//send to Servlet (EDIT)
function sendToServlet(id, email, username, password, accType) {

    var jsonData = {
        id: id,
        email: email,
        username: username,
        password: password,
        accType: accType
    };

    var jsonString = JSON.stringify(jsonData);

    // Create a new XMLHttpRequest object
    var xmlHR = new XMLHttpRequest();

    // Configure it: POST-request to the servlet
    xmlHR.open("POST", "Acc_EditServlet", true);

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
    xmlHR.send("editData=" + jsonString);

}

//send to Servlet (DELETE)
function sendToServlet2(id) {

    var jsonData = {
        id: id,
    };

    var jsonString = JSON.stringify(jsonData);

    // Create a new XMLHttpRequest object
    var xmlHR = new XMLHttpRequest();

    // Configure it: POST-request to the servlet
    xmlHR.open("POST", "Acc_DeleteServlet", true);

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
    xmlHR.send("deleteData=" + jsonString);

}

//send to Servlet (Add)
function sendToServlet3(email, username, password, accType) {

    var jsonData = {
        email: email,
        username: username,
        password: password,
        accType: accType
    };

    var jsonString = JSON.stringify(jsonData);

    // Create a new XMLHttpRequest object
    var xmlHR = new XMLHttpRequest();

    // Configure it: POST-request to the servlet
    xmlHR.open("POST", "Acc_AddServlet", true);

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
    xmlHR.send("addData=" + jsonString);

}
