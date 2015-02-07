'use strict';

angular
    .module('otd.services.activeUser', [])
    .factory('ActiveUserService', ['$http', '$q', '$log',
        function ($http, $q, $log) {

            var activeUser = null;
            var promises = [];

            return {
                getActiveUser: function () {

                    var deferred = $q.defer();
                    if (promises.length > 0) {
                        $log.debug('ActiveUserService: Added Request to queue.');
                        promises.push(deferred);
                    } else if (activeUser == null) {
                        promises.push(deferred);
                        $log.debug('ActiveUserService: Fetching ActiveUser from Server.');
                        $http({
                            method: 'GET',
                            url: 'http://localhost:8080/api/v1/user/me'
                        }).then(function(response){
                            activeUser = response.data;
                            var i;
                            for (i = promises.length; i--;) {
                                $log.debug('ActiveUserService: Resolving the existing promises with a single server side request.');
                                promises.shift().resolve(activeUser);
                            }
                        });
                    } else {
                        $log.debug('ActiveUserService: Fetching directly from userCache.');
                        deferred.resolve(activeUser);

                    }
                    return deferred.promise;
                }
            };
        }
    ]);