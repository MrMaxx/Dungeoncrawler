'use strict';

angular
    .module('otd.util.httpErrorInterceptor', [])
    .factory('HttpErrorInterceptor', ['$q','$log', 'PinesNotifications', function($q, $log, PinesNotifications) {
        var requestRecoverer = {
            responseError: function(response) {
                PinesNotifications.notify({
                    title: 'An Error Occured: '+response.status,
                    text: 'An error occured...please revise your last action.'+response.data,
                    type: 'error'
                });
                $log.error("ResponseError "+response.status);
                return $q.reject(response);
            }
        };
        return requestRecoverer;
    }])
    .config(['$httpProvider', function($httpProvider){
        $httpProvider.interceptors.push('HttpErrorInterceptor'); //Push the interceptor here
    }]);;
