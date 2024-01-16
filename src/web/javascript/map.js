var map;
var geocoder;
var directionsService;
var directionsRenderer;
var addresses;
var request;
var tempCord = [];

//fixed coordinates 
const origin = {lat: 1.5336, lng: 103.6819}; //SUC

//onload
async function initMap() {
    
    geocoder = new google.maps.Geocoder();
    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer();

    // Create a new map centered at a default location
    map = new google.maps.Map(document.getElementById('googleMap'), {
        center: origin,
        zoom: 15
    });
    directionsRenderer.setMap(map); //Render everything on map

    getData();
}

// Use the google.maps.Marker class to add markers for each stops (waypoint)
function addMarker(location, map, name) {

    new google.maps.Marker({
        position: location,
        map,
        title: name,
    });

}


//Convert plain-text Address to coordinates
function geoAddress(address, name) {

    var ad = address;

    geocoder.geocode({'address': address}, function (results, status) {

        longtitude = results[0].geometry.location.lng();
        latitude = results[0].geometry.location.lat();
        //tempCord.push("{lat: " + longtitude + "," + " lng: " + latitude + "}");
        if (status == 'OK') {
            map.setCenter(results[0].geometry.location);

            //addMarker(results[0].geometry.location, map, name);
            //request.waypoints.push({ location: results[0].geometry.location, stopover: true });

            console.log("Location: " + longtitude + ", " + latitude);
            console.log(typeof longtitude);

        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}

//get Addresses
async function getData() {

    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/FYP/MapData', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // Parse the JSON response
            addresses = JSON.parse(xhr.responseText);
            
            for (var i = 0; i < addresses.length; i++) {
                console.log(addresses[i]);
                geoAddress(addresses[i], i);
            }
        } else {
            console.error('Failed to fetch addresses');
        }
    };

    xhr.send();

}

//Setup route
function routeConfig() {
    
    if (addresses && addresses.length >= 2) {
        //setup waypoints
        var waypoints = addresses.slice(0, -1).map(function (address) {
            return { location: address, stopover: true };
        });

        request = {
            origin: origin,
            destination: addresses[addresses.length - 1],
            waypoints: waypoints,
            travelMode: 'DRIVING'
        };

        // Call the directions service to calculate the route
        directionsService.route(request, function (response, status) {
            if (status === 'OK') {
                directionsRenderer.setDirections(response);
                arrowDirect(response.routes[0]);
            } else {
                alert('Failed to calculate the route: ' + status);
            }
        });
    } else {
        alert('Insufficient addresses for route calculation.');
    }
}


//Well, arrow directions
function arrowDirect(x) {

    if (x && x.legs) {
        for (l = 0; l < x.legs.length; ++l) {

            var leg = x.legs[l];

            for (var s = 0; s < leg.steps.length; s++) {
                var step = leg.steps[s],
                        startpoint = (step.lat_lngs.length) ? step.lat_lngs[0] : step.start_point,
                        endpoint = (step.lat_lngs.length) ? step.lat_lngs[1] : step.end_point,
                        dir = ((Math.atan2(endpoint.lng() - startpoint.lng(), endpoint.lat() - startpoint.lat()) * 180) / Math.PI) + 360,
                        ico = ((dir - (dir % 3)) % 120);

                new google.maps.Marker({
                    position: startpoint,
                    icon: new google.maps.MarkerImage('http://maps.google.com/mapfiles/dir_' + ico + '.png',
                            new google.maps.Size(24, 24),
                            new google.maps.Point(0, 0),
                            new google.maps.Point(12, 12)),

                    map: map,
                    title: Math.round((dir > 360) ? dir - 360 : dir) + '°'
                });
            }
        }
    }
}


async function calculateShortestDistances() {
    // Replace these addresses with your actual addresses


    // Convert addresses to LatLng coordinates
    const coordinates = await getCoordinates(addresses);

    // Calculate and display the shortest distances
    calculateDistancesMatrix(coordinates);
}

async function getCoordinates(addresses) {
    const coordinates = [];

    for (const address of addresses) {
        const result = await geocodeAddress(address);
        if (result) {
            coordinates.push(result);
        }
    }

    return coordinates;
}

function geocodeAddress(address) {
    return new Promise((resolve) => {
        geocoder.geocode({ address: address }, (results, status) => {
            if (status === "OK" && results.length > 0) {
                const location = results[0].geometry.location;
                resolve({ lat: location.lat(), lng: location.lng() });
            } else {
                console.error(`Geocode request failed for ${address} with status ${status}`);
                resolve(null);
            }
        });
    });
}

function calculateDistancesMatrix(coordinates) {
    const origins = coordinates.map(coord => new google.maps.LatLng(coord.lat, coord.lng));
    const destinations = coordinates.map(coord => new google.maps.LatLng(coord.lat, coord.lng));

    service.getDistanceMatrix(
        {
            origins: origins,
            destinations: destinations,
            travelMode: 'DRIVING',
            unitSystem: google.maps.UnitSystem.METRIC,
        },
        displayDistances
    );
}

function displayDistances(response, status) {
    if (status === 'OK') {
        const rows = response.rows;

        for (let i = 0; i < rows.length; i++) {
            const elements = rows[i].elements;

            for (let j = 0; j < elements.length; j++) {
                const distanceText = elements[j].distance.text;
                const originAddress = addresses[i];
                const destinationAddress = addresses[j];

                console.log(`Distance from ${originAddress} to ${destinationAddress}: ${distanceText}`);
            }
        }
    } else {
        console.error('Error calculating distances:', status);
    }
}