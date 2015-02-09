'use strict';

angular
    .module('otd.services.auth', [])

    .factory('AuthenticationService',[ '$http', '$rootScope', '$log', 'Base64', 'Constants',
        function($http, $rootScope, $log, Base64, Constants) {
        // We first define a private API for our service.

        var loggedIn = false;
        var refreshToken = null;
        var accessToken = null;

        function isLoggedIn(){
            return loggedIn;
        }

        function logIn(username, password) {

            return $http({
                method: 'POST',
                url: Constants.API_BASEURL+'/oauth/token',
                headers: {
                    'Authorization': 'Basic '+Base64.encode('webapp:password'),
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: $.param({
                    password:password,
                    username:username,
                    grant_type:'password',
                    scope:'read'
                })}
            ).success(
                function(data, status, headers, config) {
                    accessToken = data.access_token;
                    refreshToken = data.refresh_token;
                    loggedIn = true;

                    $http.defaults.headers.common.Authorization = 'Bearer '+accessToken;

                    $rootScope.$broadcast('Auth:loggedIn');
                }
            ).error(
                function(data, status, headers, config) {
                    $log.error("AuthError: "+status);
                }
            );

        }

        function logOut() {
            accessToken = null;
            refreshToken = null;
            loggedIn = false;

            $http.defaults.headers.common.Authorization = 'Basic '+Base64.encode('webapp:password');

            $rootScope.$broadcast('Auth:loggedOut');
        }

        function refresh(successCallBack, errorCallBack) {

            $http({
                method: 'POST',
                url: Constants.API_BASEURL+'/oauth/token',
                headers: {
                    'Authorization': 'Basic '+Base64.encode('webapp:password'),
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: $.param({
                    grant_type:'refresh_token',
                    refresh_token: refreshToken
                })
            }).
            success(function(data, status, headers, config) {
                accessToken = data.access_token;
                refreshToken = data.refresh_token;
                loggedIn = true;

                $http.defaults.headers.common.Authorization = 'Bearer '+accessToken;

                successCallBack(refreshToken);
            }).
            error(errorCallBack);
        }

        return {
            logIn: logIn,
            logOut: logOut,
            isLoggedIn: isLoggedIn,
            refresh: refresh
        };
    }]);
