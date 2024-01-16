<%-- 
    Document   : login
    Created on : 09-Jul-2023, 17:58:57
    Author     : kenneth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <meta name="view-transition" content="same-origin"/>
        <!-- Misc -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

        <!-- Title -->
        <title>Login Page</title>

        <!-- Hyperlinks -->
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/login.css">
        <script type = "text/javascript" src="../javascript/transition.js"></script> 
    </head>

    <body>
        <div class="container">
            <div class="wrapper">
                <div class="title"><span>Sign in</span></div>

                <form action="<%= request.getContextPath()%>/LoginServlet" method="post">

                    <!-- content -->
                    <div class="row">
                        <i class="fas fa-user"></i>
                        <input type="text" name="username" placeholder="Email or username" required>
                    </div>
                    <div class="row">
                        <i class="fas fa-lock"></i>
                        <input type="password" name="password" placeholder="Password" required>
                    </div>
                    <div class="row2">
                        <span style="color:red;">${errorMessage}</span> <!-- Space to display errors -->
                    </div>
                    <div class="row button">
                        <input type="submit" value="Login">
                    </div>
                    <div class="signup-link">Not a member? <a href ='register.jsp'>Register now</a></div>
            </div>
        </div>
    </form>
</body>
</html>
