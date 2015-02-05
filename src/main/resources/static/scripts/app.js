'use strict';

angular
    .module('themesApp', [

        'http-auth-interceptor',

        'toggle-switch',
        'ui.bootstrap',
        'ui.select2',
        'ngGrid',
        'xeditable',


        'otd.controller.navigation',
        'otd.controller.userstable',
        'otd.controller.login',
        'otd.controller.headerUserBar',

        'otd.services.auth',
        'otd.services.base64',
        'otd.services.user',

        'otd.templates.TemplatesCache',
        'otd.templates.TemplatesCacheOverride',

        'otd.shared.Directives',

        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ngRoute',
        'ngAnimate'
    ])
    .controller('MainController', ['$scope', '$log', 'AuthenticationService', 'authService', 'UserService', function ($scope, $log, AuthenticationService, authService, UserService) {
        $scope.style_fixedHeader = true;
        $scope.style_headerBarHidden = false;
        $scope.style_layoutBoxed = false;
        $scope.style_fullscreen = false;
        $scope.style_leftbarCollapsed = false;
        $scope.style_leftbarShown = true;
        $scope.style_rightbarCollapsed = true;
        $scope.style_isSmallScreen = false;
        $scope.style_showSearchCollapsed = true;
        $scope.style_layoutHorizontal = false;


        /* http-auth-interceptor Event handling
        *  and modifying Authorization Headers in
        *  buffered $http Requests that failed due to a 401 before
        */
        $scope.$on('event:auth-loginRequired', function (event, newVal) {
            AuthenticationService.refresh(
                function(newAccessToken){
                    authService.loginConfirmed(
                        'success',
                        function(config){
                            config.headers["Authorization"] = 'Bearer '+newAccessToken;
                            return config;
                        }
                    )
                },
                function(){
                    $location.path("/login");
                }
            );
        });

        /*
         * Maintaining login status globally through MainController
         */
        $scope.isLoggedIn = AuthenticationService.isLoggedIn();
        $scope.$on('Auth:loggedOut', function (event, newVal) {
            $scope.isLoggedIn = AuthenticationService.isLoggedIn();
            $scope.activeUser = {};
        });
        $scope.$on('Auth:loggedIn', function (event, newVal) {
            $scope.isLoggedIn = AuthenticationService.isLoggedIn();

            UserService.activeUser().then(
                function (response) {
                    $scope.activeUser = response.data;
                    $scope.activeUserName = response.data.username;
                },
                function (errorPayload) {
                    $log.info("Could not get the active User. Responsecode=" + status);
                }
            );
        });

    }])

    .config(['$provide', '$routeProvider', function ($provide, $routeProvider) {
        $routeProvider
            .when('/', { templateUrl: 'views/home.html' })
            .when('/dashboard', { templateUrl: 'views/dashboard.html' })
            .when('/login', { templateUrl: 'views/login.html' })
            .when('/user/edit', { templateUrl: 'views/user/edit.html' })

            .otherwise({
                redirectTo: '/'
            });
    }]);
