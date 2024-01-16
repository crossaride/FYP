<%-- 
    Document   : account-admin
    Created on : 15-Aug-2023, 14:54:07
    Author     : kenne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Misc -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

        <!-- Title -->
        <title>My Profile</title>
        <!-- Hyperlinks -->
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/customerDashboard.css"> 
    </head>

    <body>       
        <div class="container bootstrap snippets bootdey">
            <div class="row">
                <div class="panel">
                    <!-- Hyperlinks -->
                </div>
                
                <div class="profile-nav col-md-3">
                    <div class="panel">
                        <div class="user-heading round">
                            <a href="#">
                                <img src="<%= request.getContextPath() %>/ProfilePicServlet" alt="image">
                            </a>
                            <h1><%= session.getAttribute("myName") %></h1>
                            <p><%= session.getAttribute("username") %></p>
                        </div>

                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="<%= request.getContextPath() %>/CustomerDashboardServlet"> <i class="fa fa-home"></i> Home</a></li>
                            <li><a href="#"> <i class="fa fa-file"></i>  My Orders <span class="label label-warning pull-right r-activity">9</span></a></li>
                            <li><a href="<%= request.getContextPath() %>/RequestDeliveryServlet"> <i class="fa fa-truck"></i> Request Delivery</a></li>
                            <li><a href="#"> <i class="fa fa-download"></i> Print Tag</a></li>
                        </ul>
                    </div>
                </div>
                
                <div class="profile-info col-md-9">          
                    <div class="panel">
                        <div class="bio-graph-heading">
                           Customer Dashboard
                        </div>
                        
                        <div class="panel-body bio-graph-info">
                            <h1>My Profile <button class="btnEdit" onclick ="alert()" style="position: relative; top: 0px; left: 670px; background: none; border: transparent" ><i class="fa fa-edit"></i></button>
                            </h1>
                            
                            <div class="row">
                                <div class="bio-row">
                                    <p><span>Username </span>: <%= session.getAttribute("myUserName") %></p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Real Name </span>: <%= session.getAttribute("myName") %></p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Email </span>: <%= session.getAttribute("username") %></p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Phone </span>: <%= session.getAttribute("myPhone") %></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="panel">
                                    <div class="panel-body">
                                        <div class="bio-chart">
                                            <div style="display:inline;width:100px;height:100px;">
                                                <canvas width="100" height="100px"></canvas>
                                                <i class="fa fa-credit-card fa-3x" style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -65px;"></i></div>
                                        </div>
                                        <div class="bio-desk">
                                            <h4 class="red">Your balance:</h4>
                                            <p>RM : 6.40</p>
                           
                                        </div>
                                    </div>
                                </div>
                            </div>
                         <!--   <div class="col-md-6">
                                <div class="panel">
                                    <div class="panel-body">
                                        <div class="bio-chart">
                                            <div style="display:inline;width:100px;height:100px;"><canvas width="100" height="100px"></canvas><input class="knob" data-width="100" data-height="100" data-displayprevious="true" data-thickness=".2" value="63" data-fgcolor="#4CC5CD" data-bgcolor="#e8e8e8" style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(76, 197, 205); padding: 0px; -webkit-appearance: none; background: none;"></div>
                                        </div>
                                        <div class="bio-desk">
                                            <h4 class="terques">Placeholder </h4>
                                            <p>Placeholder2</p>
                                            <p>Placeholder3</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="panel">
                                    <div class="panel-body">
                                        <div class="bio-chart">
                                            <div style="display:inline;width:100px;height:100px;"><canvas width="100" height="100px"></canvas><input class="knob" data-width="100" data-height="100" data-displayprevious="true" data-thickness=".2" value="75" data-fgcolor="#96be4b" data-bgcolor="#e8e8e8" style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(150, 190, 75); padding: 0px; -webkit-appearance: none; background: none;"></div>
                                        </div>
                                        <div class="bio-desk">
                                            <h4 class="green">Placeholder</h4>
                                            <p>Placeholder2</p>
                                            <p>Placeholder3</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="panel">
                                    <div class="panel-body">
                                        <div class="bio-chart">
                                            <div style="display:inline;width:100px;height:100px;"><canvas width="100" height="100px"></canvas><input class="knob" data-width="100" data-height="100" data-displayprevious="true" data-thickness=".2" value="50" data-fgcolor="#cba4db" data-bgcolor="#e8e8e8" style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(203, 164, 219); padding: 0px; -webkit-appearance: none; background: none;"></div>
                                        </div>
                                        <div class="bio-desk">
                                            <h4 class="purple">Placeholder</h4>
                                            <p>Placeholder2</p>
                                            <p>Placeholder3</p>
                                        </div>
                                    </div>
                                </div>
                            </div> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
