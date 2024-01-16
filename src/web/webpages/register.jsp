<%-- 
    Document   : register
    Created on : 03-Aug-2023, 21:18:41
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
        <title>Register Page</title>

        <!-- Hyperlinks -->
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/register.css"> 
    </head>

    <body>
        <div class="container">
            <div class="wrapper">
                <div class="back"><a href="login.jsp" class="previous">&#x25c0; Back</a></div>
                <div class="title"><span>Sign up</span></div>

                <form action="<%= request.getContextPath()%>/RegisterServlet" method="post">

                    <div class="row">                 
                        <input type="email" name="email" placeholder="Enter an email" required>
                    </div>
                    <div class="row">                      
                        <input type="password" name="password" placeholder="Enter a password" required>
                    </div>
                    <div class="row">           
                        <input type="password" name="password2" placeholder="Confirm password" required>
                    </div>
                    <div class="row2">
                        <span style="color:red;">${errorMessage}</span>
                    </div>
                    <div class="row button">
                        <input type="submit" value="Register">
                    </div>     
                    <div class="space"></a></div>
            </div>
        </div>
    </form>
</body>
</html>
