window.onload = getMyLocation

var ourCoords = {
	latitude: 37.383116,
	longitude: -121.971931
};

var options = {
	enableHighAccuracy: true,
	timeout:300000,
	maximumAge:200
};

var map;

function getMyLocation() {
	if(navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(displayLocation,displayError);
	}
	else {
		alert("Sorry, there is no geolocation support");
	}
}

function displayLocation(position) {
	var latitude = position.coords.latitude;
	var longitude = position.coords.longitude;
	var div = document.getElementById("location");
	div.innerHTML = "You are at Latitude: "+latitude+ ", Longitude: "+longitude;	
	div.innerHTML += " (with " + position.coords.accuracy+ " meters accuracy)";
	
	var km = computeDistance(position.coords, ourCoords);
	
	var dist = document.getElementById("distance");
	dist.innerHTML = "You are " +km+ " km from UCSC Extension";
	
	showMap(position.coords);
}

function displayError(error) {
	switch(error.code) {
		case error.TIMEOUT:
			alert('Geolocation request timed out');
			break;
		case error.POSITION_UNAVAILABLE:
			alert('Geolocation Position unavailable');
			break;
		case error.PERMISSION_DENIED:
			alert('Geolocation Permission denied');
			break;
		default:
			alert('Geolocation returned an unknown error code: '+ error.code);
	}
}

function computeDistance(startCoords, destCoords) {
	var startLatRads = degreesToRadians(startCoords.latitude);
	var startLongRads = degreesToRadians(startCoords.longitude);
	var destLatRads = degreesToRadians(destCoords.latitude);
	var destLongRads = degreesToRadians(destCoords.longitude);	
	var Radius = 6371; // radius of the earth in km
	var distance = Math.acos(Math.sin(startLatRads) * Math.sin(destLatRads) + Math.cos(startLatRads) * Math.cos(destLatRads) * Math.cos(startLongRads-destLongRads))* Radius;		
	return distance;
}

function degreesToRadians(degrees) {	
	var radians = (degrees * Math.PI)/180;
	return radians;
}


function showMap(coords) {
	var googleLatAndLong = new google.maps.LatLng(coords.latitude,coords.longitude);
	var mapOptions = {
		zoom: 10,
		center: googleLatAndLong,mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	var mapDiv = document.getElementById("map");
	map = new google.maps.Map(mapDiv, mapOptions);	
	
	//add marker
	var title = "Your Location";
	var content = "Your are here " + coords.latitude + " , " + coords.longitude;
	addMarker(map, googleLatAndLong, title, content);
}

function addMarker(map, latlong, title, content) {
	var markerOptions = {
		position: latlong,
		map: map,
		title: title,
		clickable: true
	};
	var marker = new google.maps.Marker(markerOptions);
	
	var infoWindowOptions = {
		content: content,
		position: latlong
	};
	var infoWindow = new google.maps.InfoWindow(infoWindowOptions);
	google.maps.event.addListener(marker, "click", 
			function() { 
				infoWindow.open(map);
			}
		);
}