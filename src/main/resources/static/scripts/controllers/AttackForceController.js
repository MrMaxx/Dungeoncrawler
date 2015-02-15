'use strict'

angular
    .module('otd.controller.attackforce', [])
    .controller('AttackForceController',
        ['$scope', '$log', 'AttackForceService', 'AttackerBlueprintService', 'AttackForcePatternService',
        function ($scope, $log, AttackForceService, AttackerBlueprintService, AttackForcePatternService) {

            $scope.attackForcePatternId = 1;

            $scope.waveBlueprintsByWaveBlueprintId = {};
            $scope.attackerBlueprintsById = {};
            $scope.waveByWaveBlueprintId = {};

            $scope.showAttackerDetails = false;
            $scope.selectedWaveBlueprintId = null;

            $scope.chooseWaveBlueprint = function(waveBlueprintId){
                $scope.showAttackerDetails = true;
                $scope.selectedWaveBlueprintId = waveBlueprintId;
            };

            $scope.chooseAttacker = function(attackerBlueprintId){
                var currentWave = $scope.waveByWaveBlueprintId[$scope.selectedWaveBlueprintId];
                if(currentWave){
                    AttackForceService.deleteWave($scope.attackForcePatternId, currentWave.id).then(function(nothing){
                        delete $scope.waveByWaveBlueprintId[currentWave.waveBlueprintId];
                        return AttackForceService.addWave($scope.attackForcePatternId, attackerBlueprintId, $scope.selectedWaveBlueprintId);
                    }).then(function(wave){
                        $scope.waveByWaveBlueprintId[wave.waveBlueprintId] = wave;
                    });
                }else{
                    AttackForceService.addWave($scope.attackForcePatternId, attackerBlueprintId, $scope.selectedWaveBlueprintId)
                        .then(function(wave){
                            $scope.waveByWaveBlueprintId[wave.waveBlueprintId] = wave;
                        });
                }
            };

            $scope.initializeController = function( attackForcePatternId ){

                AttackForcePatternService.getAttackForcePattern(attackForcePatternId)
                    .then(function(attackForcePattern){
                        $scope.attackForcePattern = attackForcePattern;
                        var lastStart = 0;
                        angular.forEach(attackForcePattern.waveBlueprints, function(waveBlueprint, index){
                            waveBlueprint.delayAfterLastWave = waveBlueprint.dispatchesAfter - lastStart;
                            lastStart = waveBlueprint.dispatchesAfter - lastStart;

                            $scope.waveBlueprintsByWaveBlueprintId[waveBlueprint.id] = waveBlueprint;
                        });
                    });

                AttackerBlueprintService.getAttackerBlueprints()
                    .then(function(attackerBlueprints){
                        angular.forEach(attackerBlueprints, function(attackerBlueprint, index){
                            $scope.attackerBlueprintsById[attackerBlueprint.id] = attackerBlueprint;
                        });
                    });

                AttackForceService.getAttackForceByAttackForcePatternId(attackForcePatternId)
                    .then(function(attackForce){
                        angular.forEach(attackForce.waves, function(wave, index){
                            $scope.waveByWaveBlueprintId[wave.waveBlueprintId] = wave;
                        });
                    });
            };
            $scope.initializeController($scope.attackForcePatternId);

        }]);