'use strict'

angular
    .module('otd.controller.dungeon', [])
    .controller('DungeonController',
        ['$scope', '$log', 'TowerBlueprintService', 'DungeonBlueprintService', 'DungeonService','$injector',
        function ($scope, $log, TowerBlueprintService, DungeonBlueprintService, DungeonService, $injector) {

            $scope.dungeonBlueprint = {};
            $scope.constructionSites = {};
            $scope.towerBlueprints = {};
            $scope.dungeon = {};
            $scope.towers = {};

            $scope.showTowerDetails = false;

            $scope.constructionSiteIdSelected = null;
            $scope.towerIdSelected = null;

            $scope.inititlizeController = function(dungeonBlueprintId){
                $scope.dungeonBlueprint = {};
                $scope.constructionSites = {};
                $scope.towerBlueprints = {};
                $scope.dungeon = {};
                $scope.towers = {};
                DungeonBlueprintService.getDungeonBlueprint(dungeonBlueprintId).then(function(dungeonBlueprint){
                    angular.forEach(dungeonBlueprint.constructionSites, function(site, index){
                        site['highlight'] = false;
                        $scope.constructionSites[site.id] = site;
                    });
                    $scope.dungeonBlueprint = dungeonBlueprint;
                    return DungeonService.getDungeonByDungeonBlueprintId(dungeonBlueprintId);
                }).then(function(dungeon){
                    angular.forEach(dungeon.towers, function(tower, index){
                        $scope.prepareTower(tower);
                    });
                    $scope.dungeon = dungeon;
                });

                TowerBlueprintService.getTowerBlueprints().then(function(towerBlueprints){
                    angular.forEach(towerBlueprints, function(towerBlueprint, index){
                        $scope.towerBlueprints[towerBlueprint.id] = towerBlueprint;
                    });
                });
            };
            $scope.inititlizeController(2);

            $scope.prepareTower = function(tower){
                var towerBlueprint = $scope.towerBlueprints[tower.towerBlueprintId];
                var site = $scope.constructionSites[tower.constructionSiteId];
                if(site){
                    tower['x'] = site.x;
                    tower['y'] = site.y;
                }
                if(towerBlueprint){
                    tower['type'] = towerBlueprint.type;
                }
                $scope.towers[tower.id] = tower;
            }

            $scope.highlightConstructionSite = function(siteId){
                angular.forEach($scope.constructionSites, function(site, index){
                    site['highlight'] = false;
                });
                $scope.constructionSites[siteId].highlight = true;

                $scope.highlightTowerBlueprint();

                $scope.showTowerDetails = true;
                $log.info("clicked Site "+siteId);
            }
            $scope.constructionSiteClicked = function(siteId){
                $scope.constructionSiteIdSelected = siteId;
                $scope.towerIdSelected = null;
                $scope.highlightConstructionSite(siteId);

            };
            $scope.towerClicked = function(towerId, siteId){
                $scope.constructionSiteIdSelected = siteId;
                $scope.towerIdSelected = towerId;
                $scope.highlightConstructionSite(siteId);

                $scope.highlightTowerBlueprint(towerId);

                $log.info("clicked Tower "+towerId);
            };
            $scope.highlightTowerBlueprint = function(towerId){
                angular.forEach($scope.towerBlueprints, function(towerBlueprint, index){
                    towerBlueprint['selected'] = false;
                });
                if(towerId){
                    var tower = $scope.towers[towerId];
                    $scope.towerBlueprints[tower.towerBlueprintId].selected = true;
                }
            }

            // preventing Massive Clicking to break the Editor
            $scope.operationRunning = false;

            $scope.chooseTower = function(towerBlueprintId){

                if( ! $scope.operationRunning ){
                    $scope.operationRunning = true;
                    if($scope.towerIdSelected != null){
                        DungeonService.deleteTower($scope.dungeon.id, $scope.towerIdSelected).then(function(response){
                            return DungeonService.addTower($scope.dungeon.id, $scope.constructionSiteIdSelected, towerBlueprintId);
                        }).then(function(tower){
                            delete $scope.towers[$scope.towerIdSelected];
                            $scope.prepareTower(tower);
                            $scope.towerIdSelected = tower.id;
                            $scope.highlightTowerBlueprint(tower.id);
                            $scope.operationRunning = false;
                        });
                    }else{
                        DungeonService.addTower($scope.dungeon.id, $scope.constructionSiteIdSelected, towerBlueprintId)
                        .then(function(tower){
                                delete $scope.towers[$scope.towerIdSelected];
                                $scope.prepareTower(tower);
                                $scope.towerIdSelected = tower.id;
                                $scope.highlightTowerBlueprint(tower.id);
                                $scope.operationRunning = false;
                            });
                    }
                }

            };

        }]);