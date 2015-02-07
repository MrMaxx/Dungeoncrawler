'use strict';

angular
    .module('otd.services.user', [])

    .service('UserService',[ '$http', '$q', 'ActiveUserService', function UserService($http, $q, ActiveUserService) {

        this.getUserStatistic = function() {

            var deferred = $q.defer();

            ActiveUserService.getActiveUser().then(function(activeUser){
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/api/v1/user/'+activeUser.id+'/statistic'
                }).then(function(response){
                    return deferred.resolve(response.data);
                });
            });

            return deferred.promise;
        }

    }]);
