'use strict';

angular
    .module('otd.services.attackforce', [])

    .factory('AttackForceService', ['$http', '$q', '$log', 'Constants', 'ActiveUserService',
        function ($http, $q, $log, Constants, ActiveUserService) {

            var attackForceByAttackForcePatternId = null;

            function clearCache(){attackForceByAttackForcePatternId = null;}
            function deleteWave(attackForceId, waveId) {
                var deferred = $q.defer();

                ActiveUserService.getActiveUser().then(function(activeUser){
                    $log.debug('AttackForceService: Deleting Wave '+waveId+' from AttackForce '+attackForceId);
                    $http({
                        method: 'DELETE',
                        url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+'/attackForce/'+attackForceId+'/wave/'+waveId
                    }).then(function(response){
                        attackForceByAttackForcePatternId = null;
                        deferred.resolve(response.data);
                    });
                });

                return deferred.promise;
            }
            function addWave(attackForceId, attackerBlueprintId, waveBlueprintId) {
                var deferred = $q.defer();

                ActiveUserService.getActiveUser().then(function(activeUser){
                    $log.debug('AttackForceService: Adding Wave to AttackForce '+attackForceId+ ' for WaveBlueprint '+waveBlueprintId+' and chosen AttackerBlueprint '+attackerBlueprintId);
                    $http({
                        method: 'POST',
                        url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+'/attackForce/'+attackForceId+'/wave',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        data: $.param({
                            attackerBlueprintId:attackerBlueprintId,
                            waveBlueprintId:waveBlueprintId
                        })
                    }).then(function(response){
                        attackForceByAttackForcePatternId = null;
                        deferred.resolve(response.data);
                    });
                });

                return deferred.promise;
            }
            function getAttackForceByAttackForcePatternId(attackForcePatternId){
                var deferred = $q.defer();

                if(attackForceByAttackForcePatternId == null){
                    $log.debug('AttackForceService: AttackForce not fetched, fetching AttackForce from Server.');
                    getAttackForces().then(function(){
                       deferred.resolve(attackForceByAttackForcePatternId[attackForcePatternId]);
                    });
                }else{
                    $log.debug('AttackForceService: fetching AttackForce from Cache.');
                    deferred.resolve(attackForceByAttackForcePatternId[attackForcePatternId]);
                }
                return deferred.promise;
            }
            function getAttackForces() {
                var deferred = $q.defer();

                ActiveUserService.getActiveUser().then(function(activeUser){

                    $log.debug('AttackForceService: Fetching AttackForces from Server.');
                    $http({
                        method: 'GET',
                        url: Constants.API_BASEURL+'/api/v1/user/'+activeUser.id+"/attackForce"
                    }).then(function(response){
                        attackForceByAttackForcePatternId = {};
                        angular.forEach(response.data, function(attackForce, index){
                            attackForceByAttackForcePatternId[attackForce.attackForcePatternId] = attackForce;
                        });
                        deferred.resolve(response.data);
                    });
                });

                return deferred.promise;
            }
            return {
                clearCache:clearCache,
                getAttackForceByAttackForcePatternId: getAttackForceByAttackForcePatternId,
                getAttackForces: getAttackForces,
                deleteWave: deleteWave,
                addWave: addWave
            }
        }]);