'use strict'

angular
    .module('otd.controller.headerUserBar', [])
    .controller('HeaderUserBarController',
        ['$scope', '$location', '$log', 'AuthenticationService',
        function ($scope, $location, $log, AuthenticationService) {


            $scope.logOut = function(){
                AuthenticationService.logOut();
                $location.path('/');
            };

    }]);
