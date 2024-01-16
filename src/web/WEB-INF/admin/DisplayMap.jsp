<%-- 
    Document   : DisplayMap
    Created on : 05-Jan-2024, 15:51:33
    Author     : kenneth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Map</title>
        <!-- Links -->
        <script type = "text/javascript" src="<%= request.getContextPath()%>/javascript/map.js"></script>  
        
        <!-- API KEY -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBcS45qAitGYHK0KOgjKJ467mDJ4pezwMs&callback=initMap" async defer></script>
    </head>

    <body onload="initMap()">
        <div id="googleMap" style="height: 400px;"></div>
        <input type = "button" onclick = "routeConfig()" value = "Show Route"> 
        
    </body>


</html>
