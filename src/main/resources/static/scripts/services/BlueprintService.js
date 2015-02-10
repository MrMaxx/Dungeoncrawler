'use strict';

angular
    .module('otd.services.blueprints', [])

    .factory('AttackerBlueprintService', ['$http', '$q', '$log', 'Constants',
        function ($http, $q, $log, Constants) {
            var attackerBlueprints = null;
            return {
                getAttackerBlueprint: function (id) {
                    var deferred = $q.defer();
                    if(attackerBlueprints == null){
                        this.getAttackerBlueprints().then(function(result){
                            angular.forEach(result, function(data, index){
                                if(data.id == id){
                                    return deferred.promise.resolve(data);
                                }
                            });
                            return deferred.promise.resolve({});
                        });
                    }else{
                        angular.forEach(attackerBlueprints, function(data, index){
                            if(data.id == id){
                                return deferred.promise.resolve(data);
                            }
                        });
                        return deferred.promise.resolve({});
                    }

                    return deferred.promise;
                },
                getAttackerBlueprints: function () {
                    var deferred = $q.defer();
                    if(attackerBlueprints == null){
                        $log.debug('AttackerBlueprintService: Fetching AttackerBlueprints from Server.');
                        $http({
                            method: 'GET',
                            url: Constants.API_BASEURL+'/api/v1/attackerBlueprint'
                        }).then(function(response){
                            attackerBlueprints = response.data;
                            deferred.promise.resolve(response.data);
                        });
                    }else {
                        $log.debug('AttackerBlueprintService: Fetching AttackerBlueprints directly from Cache.');
                        deferred.resolve(attackerBlueprints);
                    }

                    return deferred.promise;
                }
            }
        }])
    .factory('TowerBlueprintService', ['$http', '$q', '$log', 'Constants',
        function ($http, $q, $log, Constants) {
            var towerBlueprints = null;
            return {
                getTowerBlueprint: function (id) {
                    var deferred = $q.defer();
                    if(attackerBlueprints == null){
                        this.getAttackerBlueprints().then(function(result){
                            angular.forEach(result, function(data, index){
                                if(data.id == id){
                                    return deferred.promise.resolve(data);
                                }
                            });
                            return deferred.promise.resolve({});
                        });
                    }else{
                        angular.forEach(attackerBlueprints, function(data, index){
                            if(data.id == id){
                                return deferred.promise.resolve(data);
                            }
                        });
                        return deferred.promise.resolve({});
                    }

                    return deferred.promise;
                },
                getTowerBlueprints: function () {
                    var deferred = $q.defer();
                    if(towerBlueprints == null){
                        $log.debug('TowerBlueprintService: Fetching TowerBlueprints from Server.');
                        $http({
                            method: 'GET',
                            url: Constants.API_BASEURL+'/api/v1/towerBlueprint'
                        }).then(function(response){
                            towerBlueprints = response.data;
                            deferred.promise.resolve(response.data);
                        });
                    }else {
                        $log.debug('TowerBlueprintService: Fetching TowerBlueprints directly from Cache.');
                        deferred.resolve(towerBlueprints);
                    }

                    return deferred.promise;
                }
            }
        }])
    .factory('DungeonBlueprintService', ['$http', '$q', '$log', 'Constants',
        function ($http, $q, $log, Constants) {
            var dungeonBueprints = null;
            return {
                getDungeonBlueprints: function () {
                    var deferred = $q.defer();
                    if(dungeonBueprints == null){
                        $log.debug('DungeonBlueprintService: Fetching DungeonBlueprints from Server.');
                        $http({
                            method: 'GET',
                            url: Constants.API_BASEURL+'/api/v1/dungeonBlueprint'
                        }).then(function(response){
                            dungeonBueprints = response.data;
                            deferred.promise.resolve(response.data);
                        });
                    }else {
                        $log.debug('DungeonBlueprintService: Fetching DungeonBlueprints directly from Cache.');
                        deferred.resolve(dungeonBueprints);
                    }

                    return deferred.promise;
                }
            }
        }]);