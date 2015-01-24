'use strict';

var otdApp = angular.module('otdApp', ['ngRoute']);

otdApp.value('clientAuth',
    {
        clientId: 'webapp',
        clientSecret: 'password'
    });

myApp.factory('UserService', function() {
    return {
        loggedIn : false
    };
});


otdApp.config(function($routeProvider) {
        $routeProvider
            .when('/', { templateUrl: 'templates/home.html' })
            .when('/dungeon', { templateUrl: 'templates/dungeons.html' })
            .when('/blueprints', { templateUrl: 'templates/blueprints.html' })
            .otherwise({ redirectTo: '/'});
    })

    .controller('AttackerBlueprintsCtrl', function($scope, $http){
        $http.get('http://localhost:8080/api/v1/attackerBlueprint').then(
            function(response) {
                $scope.blueprints = response.data;

            });
    })
    .controller('TowerBlueprintsCtrl', function($scope, $http){
        $http.get('http://localhost:8080/api/v1/towerBlueprint').then(
            function(response) {
                $scope.blueprints = response.data;

            });
    })
    .controller('DungeonCtrl', function($scope, $http){
        $http.get('http://localhost:8080/api/v1/dungeonBlueprint').then(
            function(response) {
                $scope.dungeons = response.data;

            });
    })
;