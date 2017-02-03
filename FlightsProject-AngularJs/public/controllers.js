/**
 * Created by Shirisha on 11/24/2016.
 */
angular.module('controllers', ['services'])
    .controller('flightsCtrl', function ($scope, flightManagement) {
        var getFlightData = flightManagement.getFlights();

        getFlightData.success(function (data) {
            $scope.carriers = data.data;
        });

        getFlightData.error(function (data, status) {
            $scope.errorMessage = status;
        });


    }).controller('flightsDetailsCtrl', function ($scope, flightManagement, $routeParams) {

    var getFlightData = flightManagement.getFlightsDetails($routeParams.flightId);

    getFlightData.success(function (data) {
        $scope.flightDtls = data.data.flights;
        $scope.carrierName = data.data.short_name;
    });

    getFlightData.error(function (data, status) {
        $scope.errorMessage = status;
    });

    }).controller('jsonFileReadCtrl', function ($scope, $http, $routeParams) {

        $http.get('../Carriers/'+$routeParams.flightName+'/'+$routeParams.jsonFile)
});

