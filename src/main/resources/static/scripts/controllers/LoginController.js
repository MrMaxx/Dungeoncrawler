'use strict'

angular
    .module('otd.controller.login', [])
    .controller('LoginController', ['$scope', '$location', 'AuthenticationService', function ($scope, $location, AuthenticationService) {

        $scope.loginForm = {}

        $scope.processForm = function(){

            // Todo: check if user is already logged in

            AuthenticationService.logIn(
                $scope.loginForm.username,
                $scope.loginForm.password,
                function(data, status){
                    $location.path("/dashboard");
                },
                function(data, status){
                    $location.path("/home");
                });



        }

    }]);
