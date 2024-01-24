<%-- 
    Document   : AdminDashboard
    Created on : 24-Jul-2023, 13:15:39
    Author     : kenneth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Merienda+One" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <!-- Title -->
        <title>Admin Dashboard</title>
        
        <!-- Hyperlink -->
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/admin.css">  
    </head>
    <body>
        <form>
            <!-- Top Menu bar -->
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" ><i class="fa fa-cube"></i>FMS</a>  		                               
                </div>

                <!-- Elements/Links, search bar of sort-->
                <div id="navbarCollapse" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <!--  <li><a href="#" class="notifications"><i class="fa fa-bell-o"></i><span class="badge">1</span></a></li> -->
                        <!--  <li><a href="#" class="messages"><i class="fa fa-envelope-o"></i><span class="badge">10</span></a></li> -->
                        <li><a><label for="">Welcome back, <%= session.getAttribute("username")%>!</label></a></li>
                        <li class="divider"></li>
                        <li><a href="LogoutServlet"><span class ="icon-red"><i class="material-icons">&#xE8AC;</i></span></li>
                    </ul>
                </div>  
            </nav>

            <!-- Side Nav bar -->
            <div id="content"> 
                <ul style="list-style-type: none; margin: 0; padding: 0; width: 200px; background-color: #f1f1f1;">
                    <li><a class="active" href="<%= request.getContextPath() %>/AdminDashboardServlet" >Home</a></li>
                    <li><a href="#news">Warehouse Management</a></li>
                    <li><a href="<%= request.getContextPath() %>/AccountManagementServlet">Account Management</a></li>
                    <li><a href="#contact">Delivery Management</a></li>
                    <li><a href="<%= request.getContextPath() %>/redirectMapServlet">View Schedule</a></li>
                </ul>
            </div>           
        </form>
    </body>
</html>
