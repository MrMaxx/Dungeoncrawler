'use strict'

angular
    .module('otd.controller.register', [])
    .controller('RegisterController', ['$scope', '$location', 'UserService', 'AuthenticationService',
        function ($scope, $location, UserService, AuthenticationService) {

        $scope.registerForm = {}

        $scope.processForm = function(){


            UserService.register(
                $scope.registerForm.username,
                $scope.registerForm.email,
                $scope.registerForm.password
            ).then(
                function(successresponse){
                    AuthenticationService.logIn(
                        $scope.registerForm.username,
                        $scope.registerForm.password
                    ).then(
                        function(data, status){
                            $location.path("/dashboard");
                        }
                    );
                },
                function(errorrepsonse){

                }
            );
        }

    }]);
