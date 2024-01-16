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
        
        <!-- Title -->
        <title>Display Map</title>
        
        <!-- Hyperlinks -->
        <script type = "text/javascript" src="<%= request.getContextPath()%>/javascript/map.js"></script>  
        <!-- API KEY -->
            <!-- Replace "YOUR_API_KEY" with your own API Key -->
            <!-- Replace "FUNCTION_NAME" with your own function name, in my case it's initMap, it will load the map. -->
        <script src=" https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=FUNCTION_NAME" async defer></script>
    </head>

    <body onload="initMap()">
        <!-- Your map -->
        <div id="googleMap" style="height: 500px;"></div> <!-- You can put css or whatever remember to define map size -->
        <input type = "button" onclick = "routeConfig()" value = "Show Route">      
    </body>


</html>
