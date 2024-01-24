<%-- 
    Document   : account-admin
    Created on : 15-Aug-2023, 14:54:07
    Author     : kenneth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Misc -->
        <link href="https://fonts.googleapis.com/css?family=Merienda+One" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <!-- Title -->
        <title>Account Management</title>
        <!-- Hyperlinks -->
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/admin.css">  
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/adminTable.css">
        <script type = "text/javascript" src="javascript/admin_table.js"></script>  
    </head>

    <!-- Auto load table -->
    <body onload="populateTable()">       
        <form>
            <!-- Menu bar -->
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" ><i class="fa fa-cube"></i>FMS</a>  		                               
                </div>

                <div id="navbarCollapse" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <!--  <li><a href="#" class="notifications"><i class="fa fa-bell-o"></i><span class="badge">1</span></a></li> -->
                        <!--  <li><a href="#" class="messages"><i class="fa fa-envelope-o"></i><span class="badge">10</span></a></li> -->

                        <li><a><label for="">Welcome back, <%= session.getAttribute("username")%>!</label></a></li>
                        <li class="divider"></li>
                        <li><a href="LogoutServlet"><span class ="icon-red"><i class="material-icons">&#xE8AC;</i></span></a></li>
                    </ul>
                </div>  
            </nav>
        </form>
        <br>

         <!-- Open form button --> 
        <button id="openForm" class="form-control" data-toggle="modal" data-target="#addModal" ><i class="fa fa-plus-circle" aria-hidden="true"></i>&nbsp; Add new</button>
        <p></p>
        <!-- Modal form config --> 
        <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title modal_head" id="label">Add Account</h4>
                        <button type="button" class="close cash-dismiss" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>

                    <div class="modal-body">
                        <label for="emailInput"> Email: </label> 
                        <input type="email"  id="emailInput" placeholder="Enter an email"> 
                        <br>
                        <label for="usernameInput"> Username: </label> 
                        <input type="text" id="usernameInput"  placeholder="Enter an username"> 
                        <br>
                        <label for="passwordInput"> Password: </label> 
                        <input type="text"  id="passwordInput"  placeholder="Enter a password"> 
                        <br>
                        <label for="accTypeInput"> Account Type: </label> 
                        <input type="text"  id="accTypeInput"  placeholder="Enter a account type"> 
                        <br>
                    </div>
                    
                    <div class="modal-footer form_footer">
                        <button class="btn btn-primary" id="addButton" value="Add" onclick="addData()" data-dismiss="modal"> Add </button>                      
                    </div>
                </div>
            </div>
        </div>

        <!-- Table header -->
        <div class="tbl-header">
            <table id="tblAcc" class="table border ps-table w-100 mb-3">
                <thead>
                    <tr>
                        <th id ="id" class="font-weight-bold py-2 border-0" >ID</th>
                        <th id ="email" class="font-weight-bold py-2 border-0 quantity">Email</th>
                        <th id ="username" class="font-weight-bold py-2 border-0 ">Username</th>
                        <th id ="password" class="font-weight-bold py-2 border-0 ">Password</th>
                        <th id ="accType" class="font-weight-bold py-2 border-0 ">Account Type</th>   
                        <th id ="action" class="font-weight-bold py-2 border-0 ">Action</th>
                    </tr>
                </thead>
            </table>
        </div>

        <!-- To separate table header color with table content color --> 
        <div class="tbl-content">
            <!-- Table Content -->
            <table id="tblCont">
                <tbody id="table-body"> 
                    <!-- Populate content with Javascript -->
                </tbody> 
            </table> 
        </div>
    </body>
</html>
