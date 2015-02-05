'use strict';

angular
    .module('otd.services.user', [])

    .service('UserService',[ '$http', function UserService($http) {

        this.activeUser = function(successCallBack, errorCallBack) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/api/v1/user/me'
            })
        }

    }]);
