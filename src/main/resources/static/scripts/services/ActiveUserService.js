'use strict';

angular
    .module('otd.services.activeUser', [])
    .factory('ActiveUserService', ['$http', '$q', '$log', 'Constants',
        function ($http, $q, $log, Constants) {

            var activeUser = null;
            var promises = [];

            return {
                clear: function(){ activeUser = null;},
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
                            url: Constants.API_BASEURL+'/api/v1/user/me'
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