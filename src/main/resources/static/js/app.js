'use strict';


angular.module('otdApp', ['ngRoute'])

    .config(function($routeProvider) {
        $routeProvider
            .when('/', { templateUrl: 'templates/home.html' })
            .when('/dungeon', { templateUrl: 'templates/dungeons.html' })
            .when('/blueprints', { templateUrl: 'templates/attackerBlueprints.html' })
            .otherwise({ redirectTo: '/'});
    })

    .controller('AttackerBlueprintsCtrl', function($scope, $http){
        $http.get('http://localhost:8080/api/v1/attackerBlueprint').then(
            function(attackerBlueprintResponse) {
                $scope.blueprints = attackerBlueprintResponse.data;

            });
    });