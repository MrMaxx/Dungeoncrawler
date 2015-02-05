'use strict'

angular
    .module('otd.controller.headerUserBar', [])
    .controller('HeaderUserBarController',
        ['$scope', '$location', '$log', 'AuthenticationService', 'UserService',
        function ($scope, $location, $log, AuthenticationService, UserService) {


            $scope.activeUser = {};
            $scope.activeUserName = '';

            $scope.logOut = function(){
                AuthenticationService.logOut();
                $location.path('/');
            };

            $scope.refreshToken = function(){
                UserService.activeUser().then(
                    function (response) {
                        $scope.activeUser = response.data;
                        $scope.activeUserName = response.data.username;
                    },
                    function (errorPayload) {
                        $log.info("Could not get the active User. Responsecode=" + status);
                    }
                );
                console.log("activeUser.username = "+$scope.activeUser.username);
                console.log("activeUserName = "+$scope.activeUserName);
            };


    }]);
