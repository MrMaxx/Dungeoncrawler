'use strict'

angular
    .module('otd.controller.userstatistics', [])
    .controller('UserStatisticsController',
    ['$scope', 'UserService',
        function ($scope, UserService) {

            UserService.getUserStatistic().then(function(userStatistics){
                $scope.userStatistics = userStatistics;
            });

        }]);
