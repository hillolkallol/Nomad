/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    $( document ).ready(function() {
	getLocation();
	var x;
	function getLocation(){
	    if (navigator.geolocation){
		navigator.geolocation.getCurrentPosition(showPosition,showError);
	    }
	    else{
		$( "#demo" ).text("Geolocation is not supported by this browser.");
	    }
	}

	function showPosition(position){
	    lat=position.coords.latitude;
	    lon=position.coords.longitude;
	    displayLocation(lat,lon);
	}

	function showError(error){
	    switch(error.code){
		case error.PERMISSION_DENIED:
		    $( "#demo" ).text("User denied the request for Geolocation.");
		break;
		case error.POSITION_UNAVAILABLE:
		    $( "#demo" ).text("Location information is unavailable.");
		break;
		case error.TIMEOUT:
		    $( "#demo" ).text("The request to get user location timed out.");
		break;
		case error.UNKNOWN_ERROR:
		    $( "#demo" ).text("An unknown error occurred.");
		break;
	    }
	}

	function displayLocation(latitude,longitude){
	    var geocoder;
	    geocoder = new google.maps.Geocoder();
	    var latlng = new google.maps.LatLng(latitude, longitude);

	    geocoder.geocode(
		{'latLng': latlng}, 
		function(results, status) {
		    if (status == google.maps.GeocoderStatus.OK) {
		        if (results[0]) {
		            var add= results[0].formatted_address ;
		            var  value=add.split(",");

		            count=value.length;
		            country=value[count-1];
		            state=value[count-2];
		            city=value[count-3];
		            $( "#demo" ).val(city);                            
//                            $("#location").click();
		        }
		        else  {
		            $( "#demo" ).text("address not found");
		        }
		    }
		    else {
		        $( "#demo" ).text("Geocoder failed due to: " + status);
		    }
		}
	    );
	}
    });
})();



