'use strict';

angular
    .module('otd.services.dungeonblueprints', [])

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
        }]);