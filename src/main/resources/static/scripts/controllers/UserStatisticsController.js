'use strict'

angular
    .module('otd.controller.userstatistics', [])
    .controller('UserStatisticsController',
    ['$scope', 'UserService', 'ActiveUserService',
        function ($scope, UserService, ActiveUserService) {

            UserService.getUserStatistic().then(function(userStatistics){
                $scope.userStatistics = userStatistics;
            });

        }]);
