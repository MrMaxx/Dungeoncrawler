'use strict'

angular
    .module('otd.controller.fights', [])
    .controller('FightsController', ['$scope', '$log', 'FightService', 'ActiveUserService', 'AuthenticationService',
        function ($scope, $log, FightService, ActiveUserService, AuthenticationService) {

            $scope.activeUserId=0;
            ActiveUserService.getActiveUser().then(function(activeUser){$scope.activeUserId = activeUser.id;});
            $scope.accessToken = AuthenticationService.getAccessToken();


            $scope.totalServerItems = 0;
            $scope.fights = [];

            $scope.initializeController = function(){
                FightService.getFights().then(function(fights){
                    angular.forEach(fights, function(fight, index){
                        fight['hasWon'] = ($scope.activeUserId == fight.attackingUserId && fight.outcome == 'ATTACKER_WON')
                            || ($scope.activeUserId == fight.defendingingUserId && fight.outcome == 'DEFENDER_WON');
                    });
                    $scope.fights = fights;
                });
            };
            $scope.initializeController();

            $scope.$on('Fight:created', function (event, newVal) {
                $scope.initializeController();
            });

            $scope.fightsOptions = {
                data: 'fights',
                columnDefs: [
                    {
                        field:'fightState',
                        displayName:'state',
                        width:50,
                        cellTemplate: '' +
                            '<div class="ngCellText" ng-class="col.colIndex()">' +
                            '   <span ng-cell-text>' +
                            '       <i style="display:{{row.getProperty(\'hasWon\') && row.getProperty(\'outcome\')==\'ATTACKER_WON\'?\'block\':\'none\'}};color:green;font-weight:bold;" class="fa fa-plus-square"></i>' +
                            '       <i style="display:{{!row.getProperty(\'hasWon\') && row.getProperty(\'outcome\')==\'ATTACKER_WON\'?\'block\':\'none\'}};color:red;font-weight:bold;" class="fa fa-plus-square"></i>' +
                            '       <i style="display:{{row.getProperty(\'hasWon\') && row.getProperty(\'outcome\')==\'DEFENDER_WON\'?\'block\':\'none\'}};color:green;font-weight:bold;" class="fa fa-minus-square"></i>' +
                            '       <i style="display:{{!row.getProperty(\'hasWon\') && row.getProperty(\'outcome\')==\'DEFENDER_WON\'?\'block\':\'none\'}};color:red;font-weight:bold;" class="fa fa-minus-square"></i>' +
                            '       <i style="display:{{!row.getProperty(\'outcome\')?\'block\':\'none\'}};font-weight:bold;" class="fa fa-square-o"></i>' +
                            '   </span>' +
                            '</div>'
                    },
                    {
                        field:'createdAt',
                        displayName:'Date',
                        width:90,
                        cellTemplate: '' +
                            '<div class="ngCellText" ng-class="col.colIndex()">' +
                            '  <span ng-cell-text>{{row.getProperty(col.field) | date:\'dd.MM HH:mm\'}}</span>' +
                            '</div>'
                    },
                    {field:'attackingUserName', displayName:'Attacker'},
                    {field:'defendingUserName', displayName:'Defender'},
                    {
                        displayName:'',
                        width:50,
                        cellTemplate:'' +
                            '<div style="padding-left:15px;padding-top:4px;">' +
                            '   <a target="_blank" href="gameclient/index.html?userId={{activeUserId}}&fightId={{row.getProperty(\'id\')}}&access_token={{accessToken}}" style="display:{{row.getProperty(\'outcome\')?\'block\':\'none\'}};">' +
                            '       <i style="font-size:20px;font-weight:bold;" class="fa fa-eye"></i>' +
                            '   </a>' +
                            '</div>'
                    }

                ],
                enablePaging: false,
                enableSorting: false,
                showFooter: false,
                totalServerItems: 'totalServerItems',
                enableCellSelection: false,
                beforeSelectionChange: function() {
                    return false;
                }
            };

        }])
