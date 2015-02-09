'use strict'

angular
    .module('otd.controller.userstable', [])
    .controller('UserInRangeController', ['$scope', '$log', 'UserService', 'ActiveUserService', function ($scope, $log, UserService, ActiveUserService) {
        $scope.filterOptions = {
            filterText: "",
            useExternalFilter: true
        };
        $scope.totalServerItems = 0;
        $scope.pagingOptions = {
            pageSizes: [15, 30, 50],
            pageSize: 15,
            currentPage: 1
        };

        // ng-grid forces me to hold a value here in my Controller
        $scope.activeUserId=0;
        ActiveUserService.getActiveUser().then(function(activeUser){$scope.activeUserId = activeUser.id;});

        $scope.getPagedDataAsync = function (pageSize, page) {
            UserService.getUserPage(page,pageSize).then(function(userPage){
                $scope.myData = userPage.users;
                $scope.totalServerItems = userPage.totalSize;
                $scope.myData = $scope.prepareData(userPage.users);
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            });
        };

        $scope.prepareData = function(userList){
            angular.forEach($scope.myData, function(data, index){
                data['isOther'] = (data.id != $scope.activeUserId);
            });
            return userList;
        }

        $scope.getUserPositionedPage = function (pageSize, page) {
            UserService.getUserRankedPage(pageSize).then(function(userPage){

                $scope.myData = $scope.prepareData(userPage.users);
                $scope.totalServerItems = userPage.totalSize;
                $scope.pagingOptions.currentPage = userPage.page;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            });
        };

        $scope.getUserPositionedPage($scope.pagingOptions.pageSize);

        $scope.$watch('pagingOptions', function (newVal, oldVal) {
            if (newVal.currentPage !== oldVal.currentPage || newVal.pageSize !== oldVal.pageSize) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, true);

        $scope.gridOptions = {
            data: 'myData',
            columnDefs: [
             {field:'rank', displayName:'Rank', width:50},
             {
                 field:'username',
                 displayName:'Name',
                 cellTemplate: '' +
                     '<div class="ngCellText" ng-class="col.colIndex()">' +
                     '  <span style="color:{{!row.getProperty(\'isOther\')?\'green\':\'black\'}}" ng-cell-text>{{row.getProperty(col.field)}}</span>' +
                     '</div>'
             },
             {field:'score', displayName:'Score', width:80},
             {
                 displayName:'Action',
                 width:80,
                 cellTemplate:'' +
                     '<div style="display:{{row.getProperty(\'isOther\')?\'block\':\'none\'}};padding:5px;line-hight:1.0;" class="buttons">' +
                     '  <button class="btn btn-sm btn-primary">Attack</button>' +
                     '</div>'}
             ],

            enablePaging: true,
            enableSorting: false,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            filterOptions: $scope.filterOptions,
            enableCellSelection: false,
            beforeSelectionChange: function() {
                return false;
            }
        };

    }])
