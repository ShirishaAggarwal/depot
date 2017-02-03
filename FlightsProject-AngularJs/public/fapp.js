/**
 * Created by Shirisha on 11/24/2016.
 */
var app = angular.module("fapp", ['controllers', 'ngRoute',]);

console.log("In fapp");
app.config(function ($routeProvider) {
    $routeProvider
        .when('/viewCarriers', {
            templateUrl: 'flights.html',
            controller: 'flightsCtrl'
        })
        .when('/viewFlights/:flightId', {
            templateUrl: 'flightDetails.html',
            controller: 'flightsDetailsCtrl'
        })
        .when('/viewJSONDetails/:flightName/:jsonFile', {
            templateUrl: 'flightDetails.html',
            controller: 'flightsDetailsCtrl'
        })
        .otherwise({
            redirectTo: '/viewCarriers'
        });
});
