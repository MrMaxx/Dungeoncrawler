'use strict';

angular
    .module('otd.services.fight', [])

    .factory('FightService', ['$http', '$q', '$log', 'Constants', 'ActiveUserService',
        function ($http, $q, $log, Constants, ActiveUserService) {

            function createFight(defendingUserId) {
                var deferred = $q.defer();

                ActiveUserService.getActiveUser().then(function(activeUser){
                    $log.debug('FightService: Creating Fight with User '+defendingUserId);
                    $http({
                        method: 'POST',
                        url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+'/fight',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        data: $.param({
                            defendingUserId:defendingUserId
                        })
                    }).then(function(response){
                        deferred.resolve(response.data);
                    });
                });

                return deferred.promise;
            }
            function getFights() {
                var deferred = $q.defer();

                ActiveUserService.getActiveUser().then(function(activeUser){
                    $log.debug('FightService: getting Fights');
                    $http({
                        method: 'GET',
                        url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+'/fight'
                    }).then(function(response){
                        deferred.resolve(response.data);
                    });
                });

                return deferred.promise;
            }
            return {
                getFights: getFights,
                createFight:createFight
            }
        }]);