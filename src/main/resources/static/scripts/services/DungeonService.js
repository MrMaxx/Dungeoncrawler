'use strict';

angular
    .module('otd.services.dungeon', [])

    .factory('DungeonService', ['$http', '$q', '$log', 'Constants', 'ActiveUserService',
        function ($http, $q, $log, Constants, ActiveUserService) {

            var dungeonsByDungeonBlueprintId = null;

            function clearCache(){dungeonsByDungeonBlueprintId = null;}
            function deleteTower(dungeonId, towerId) {
                var deferred = $q.defer();

                ActiveUserService.getActiveUser().then(function(activeUser){
                    $log.debug('DungeonService: Deleting Tower '+towerId+' from Dungeon '+dungeonId);
                    $http({
                        method: 'DELETE',
                        url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+'/dungeon/'+dungeonId+'/tower/'+towerId
                    }).then(function(response){
                        dungeonsByDungeonBlueprintId = null;
                        deferred.resolve(response.data);
                    });
                });

                return deferred.promise;
            }
            function addTower(dungeonId, constructionSiteId, towerBlueprintId) {
                var deferred = $q.defer();

                ActiveUserService.getActiveUser().then(function(activeUser){
                    $log.debug('DungeonService: Adding Tower to Dungeon '+dungeonId);
                    $http({
                        method: 'POST',
                        url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+'/dungeon/'+dungeonId+'/tower',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        data: $.param({
                            towerBlueprintId:towerBlueprintId,
                            constructionSiteId:constructionSiteId
                        })
                    }).then(function(response){
                        dungeonsByDungeonBlueprintId = null;
                        deferred.resolve(response.data);
                    });
                });

                return deferred.promise;
            }
            function getDungeonByDungeonBlueprintId(dungeonBlueprintId){
                var deferred = $q.defer();

                if(dungeonsByDungeonBlueprintId == null){
                    $log.debug('DungeonService: Dungeons not fetched, fetching Dungeons from Server.');
                    getDungeons().then(function(){
                       deferred.resolve(dungeonsByDungeonBlueprintId[dungeonBlueprintId]);
                    });
                }else{
                    $log.debug('DungeonService: fetching Dungeons from Cache.');
                    deferred.resolve(dungeonsByDungeonBlueprintId[dungeonBlueprintId]);
                }
                return deferred.promise;
            }
            function getDungeons() {
                var deferred = $q.defer();

                ActiveUserService.getActiveUser().then(function(activeUser){

                    $log.debug('DungeonService: Fetching Dungeons from Server.');
                    $http({
                        method: 'GET',
                        url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+"/dungeon"
                    }).then(function(response){
                        dungeonsByDungeonBlueprintId = {};
                        angular.forEach(response.data, function(dungeon, index){
                            dungeonsByDungeonBlueprintId[dungeon.dungeonBlueprintId] = dungeon;
                        });
                        deferred.resolve(response.data);
                    });
                });

                return deferred.promise;
            }
            return {
                clearCache:clearCache,
                getDungeonByDungeonBlueprintId: getDungeonByDungeonBlueprintId,
                getDungeons: getDungeons,
                deleteTower: deleteTower,
                addTower: addTower
            }
        }]);