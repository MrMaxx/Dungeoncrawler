'use strict'

angular
    .module('otd.controller.dungeon', [])
    .controller('DungeonController',
        ['$scope', '$log',
        function ($scope, $log) {

            $scope.dungeon = {
                "dungeonBlueprintId": 1,
                "id": 0,
                "towers": [
                    {
                        "constructionSiteId": 1,
                        "towerBlueprintId": 1,
                        "id": 1,
                        // x,y NEED TO BE ADDED ON LOAD !!!
                        "x": 200,
                        "y": 440,
                        // TowerName NEEDS TO BE ADDED ON LOAD !!!
                        "type":"GATTLING",
                        // needs to be added and initialized with false
                        "highlight":false
                    }
                ]
            };

            $scope.dungeonBlueprint = {
                "id": 1,
                "name": "SNAIL",
                "width": 1080,
                "height": 840,
                "constructionSites": [
                    {
                        "id": 1,
                        "x": 200,
                        "y": 440
                    },
                    {
                        "id": 2,
                        "x": 440,
                        "y": 280
                    }
                ]
            };

            $scope.towerBlueprints = [
                {
                    "id": 1,
                    "damage": 20,
                    "timeToReload": 100,
                    "attackRange": 4000,
                    "price": 100,
                    "type": "GATTLING",
                    // NEEDS TO BE ADDED ON STARTUP
                    "selected": true
                },
                {
                    "id": 2,
                    "damage": 30,
                    "timeToReload": 1500,
                    "attackRange": 2000,
                    "price": 200,
                    "type": "FLAMER",
                    // NEEDS TO BE ADDED ON STARTUP
                    "selected": false
                }
            ];


            $scope.constructionSiteClicked = function(siteId){

                $log.info("clicked Site "+siteId);
            };
            $scope.towerClicked = function(towerId){

                $log.info("clicked Tower "+towerId);
            };







        }]);