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
                                    deferred.resolve(data);
                                }
                            });
                            deferred.resolve({});
                        });
                    }else{
                        angular.forEach(attackerBlueprints, function(data, index){
                            if(data.id == id){
                                deferred.resolve(data);
                            }
                        });
                        deferred.resolve({});
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
                            deferred.resolve(response.data);
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
                        this.getTowerBlueprints().then(function(result){
                            angular.forEach(result, function(data, index){
                                if(data.id == id){
                                    deferred.resolve(data);
                                }
                            });
                            deferred.resolve({});
                        });
                    }else{
                        angular.forEach(attackerBlueprints, function(data, index){
                            if(data.id == id){
                                deferred.resolve(data);
                            }
                        });
                        deferred.resolve({});
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
                            deferred.resolve(response.data);
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
            var dungeonBlueprints = null;
            return {
                getDungeonBlueprint: function (id) {
                    var deferred = $q.defer();
                    if(dungeonBlueprints == null){
                        this.getDungeonBlueprints().then(function(result){
                            angular.forEach(result, function(data, index){
                                if(data.id == id){
                                    deferred.resolve(data);
                                }
                            });
                            deferred.resolve({});
                        });
                    }else{
                        angular.forEach(dungeonBlueprints, function(data, index){
                            if(data.id == id){
                                deferred.resolve(data);
                            }
                        });
                        deferred.resolve({});
                    }

                    return deferred.promise;
                },
                getDungeonBlueprints: function () {
                    var deferred = $q.defer();
                    if(dungeonBlueprints == null){
                        $log.debug('DungeonBlueprintService: Fetching DungeonBlueprints from Server.');
                        $http({
                            method: 'GET',
                            url: Constants.API_BASEURL+'/api/v1/dungeonBlueprint'
                        }).then(function(response){
                            dungeonBlueprints = response.data;
                            deferred.resolve(response.data);
                        });
                    }else {
                        $log.debug('DungeonBlueprintService: Fetching DungeonBlueprints directly from Cache.');
                        deferred.resolve(dungeonBlueprints);
                    }

                    return deferred.promise;
                }
            }
        }])
    .factory('AttackForcePatternService', ['$http', '$q', '$log', 'Constants',
        function ($http, $q, $log, Constants) {
            var attackForcePatterns = null;
            return {
                getAttackForcePattern: function (id) {
                    var deferred = $q.defer();
                    if(attackForcePatterns == null){
                        this.getAttackForcePatterns().then(function(result){
                            angular.forEach(result, function(data, index){
                                if(data.id == id){
                                    deferred.resolve(data);
                                }
                            });
                            deferred.resolve({});
                        });
                    }else{
                        angular.forEach(attackForcePatterns, function(data, index){
                            if(data.id == id){
                                deferred.resolve(data);
                            }
                        });
                        deferred.resolve({});
                    }

                    return deferred.promise;
                },
                getAttackForcePatterns: function () {
                    var deferred = $q.defer();
                    if(attackForcePatterns == null){
                        $log.debug('AttackForcePatternService: Fetching AttackForcePatterns from Server.');
                        $http({
                            method: 'GET',
                            url: Constants.API_BASEURL+'/api/v1/attackForcePattern'
                        }).then(function(response){
                            attackForcePatterns = response.data;
                            deferred.resolve(response.data);
                        });
                    }else {
                        $log.debug('AttackForcePatternService: Fetching AttackForcePatterns directly from Cache.');
                        deferred.resolve(dungeonBlueprints);
                    }

                    return deferred.promise;
                }
            }
        }]);