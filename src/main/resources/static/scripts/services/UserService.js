'use strict';

angular
    .module('otd.services.user', [])
    .service('UserService',[ '$http', '$q', 'ActiveUserService', 'Constants',
        function UserService($http, $q, ActiveUserService, Constants) {

        this.register = function(username, email, password) {
            return $http({
                method: 'POST',
                url: Constants.API_BASEURL+'/api/v1/user',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: $.param({
                    password:password,
                    username:username,
                    email:email
                })
            });
        }

        this.getUserStatistic = function() {

            var deferred = $q.defer();

            ActiveUserService.getActiveUser().then(function(activeUser){
                $http({
                    method: 'GET',
                    url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+'/statistic'
                }).then(function(response){
                    return deferred.resolve(response.data);
                });
            });

            return deferred.promise;
        }

        this.getUserPage = function(page, pagesize) {

            var deferred = $q.defer();

            if( !page || !pagesize || page < 1 || pagesize < 1){return deferred.resolve({});}

            $http({
                method: 'GET',
                url: Constants.API_BASEURL+'/api/v1/user',
                params:{page:page, pagesize:pagesize}
            }).then(function(response){
                return deferred.resolve(response.data);
            });

            return deferred.promise;
        }

        this.getUserRankedPage = function(pagesize) {

            var deferred = $q.defer();

            if( !pagesize || pagesize < 1){return deferred.resolve({});}

            ActiveUserService.getActiveUser().then(function(activeUser){
                $http({
                    method: 'GET',
                    url: Constants.API_BASEURL+'/api/v1/user',
                    params:{pagesize:pagesize, userId:activeUser.id}
                }).then(function(response){
                    return deferred.resolve(response.data);
                });
            });

            return deferred.promise;
        }

    }]);
