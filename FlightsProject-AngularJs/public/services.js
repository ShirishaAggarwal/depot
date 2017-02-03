/**
 * Created by Shirisha on 11/24/2016.
 */
angular.module('services', [])
    .factory('flightManagement', function ($http) {
        return {
            getFlights: function () {
                var flights = $http({method: 'GET', url: 'http://localhost:3010/flights/carriers'});
                return flights;
            },
            getFlightsDetails: function (carrierName) {
                var flightDetails = $http({
                    method: 'GET',
                    url: 'http://localhost:3010/flights/carriers/' + carrierName
                });
                return flightDetails;
            }
        }
    });
