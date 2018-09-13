<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>Spring Boot JSP example</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/style.css">
</head>
<body>
<img src="${pageContext.request.contextPath}img/kiwi2.jpg" width="50" height="50" alt="Girl in a jacket">
<br> Message: ${message} s33212 ěs
</body>

<a href="/map">MAP</a>
</html>

<!DOCTYPE html>
<html>
<head>
    <style>
        /* Set the size of the div element that contains the map */
        #map {
            height: 400px;  /* The height is 400 pixels */
            width: 100%;  /* The width is the width of the web page */
        }
    </style>
</head>
<body>
<h3>My Google Maps Demo</h3>
<!--The div element for the map -->
<div id="map"></div>
<script>
    // Initialize and add the map
    function initMap() {
        // The location of Uluru
        var uluru = {lat: -25.344, lng: 131.036};
        // The map, centered at Uluru
        var map = new google.maps.Map(
            document.getElementById('map'), {zoom: 4, center: uluru});
        // The marker, positioned at Uluru
        var marker = new google.maps.Marker({position: uluru, map: map});
    }

</script>
<!--Load the API from the specified URL
* The async attribute allows the browser to render the page while the API loads
* The key parameter will contain your own API key (which is not needed for this tutorial)
* The callback parameter executes the initMap() function
-->
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDM3hLUh10lPdC4qzzQ24HMuVldsSja0yk&callback=initMap">
</script>
</body>
</html>