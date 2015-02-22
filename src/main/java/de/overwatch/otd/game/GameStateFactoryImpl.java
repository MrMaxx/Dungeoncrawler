package de.overwatch.otd.game;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import de.overwatch.otd.domain.Fight;
import de.overwatch.otd.domain.attack.AttackForcePattern;
import de.overwatch.otd.domain.attack.AttackerBlueprint;
import de.overwatch.otd.domain.defend.ConstructionSite;
import de.overwatch.otd.domain.defend.DungeonBlueprint;
import de.overwatch.otd.domain.defend.DungeonNode;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.game.model.AttackerSpawn;
import de.overwatch.otd.game.model.DefenderSpawn;
import de.overwatch.otd.game.util.IdGenerator;
import de.overwatch.otd.repository.AttackForcePatternRepository;
import de.overwatch.otd.repository.AttackerBlueprintRepository;
import de.overwatch.otd.repository.DungeonBlueprintRepository;
import de.overwatch.otd.repository.TowerBlueprintRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The GameEngineFactory is responsible to load all the bits and bytes from the Database,
 * that are needed to compute the result of a {link Fight}
 */
@Service
public class GameStateFactoryImpl implements GameStateFactory {

    private static final Logger LOGGER = Logger.getLogger(GameStateFactoryImpl.class);
    @Autowired
    private DungeonBlueprintRepository dungeonBlueprintRepository;
    @Autowired
    private AttackerBlueprintRepository attackerBlueprintRepository;
    @Autowired
    private TowerBlueprintRepository towerBlueprintRepository;

    @Autowired
    private AttackForcePatternRepository attackForcePatternRepository;

    @Override
    public GameState createGameState(Fight fight) {

        List<AttackerBlueprint> attackerBlueprints = attackerBlueprintRepository.findAll();
        List<TowerBlueprint> defenderBlueprints = towerBlueprintRepository.findAll();
        DungeonBlueprint dungeonBlueprint = dungeonBlueprintRepository.findById(fight.getDungeon().getDungeonBlueprintId());

        Map<Integer,AttackerBlueprint> idToAttackerBlueprintMap = Maps.uniqueIndex(attackerBlueprints, new Function<AttackerBlueprint, Integer>() {
            public Integer apply(AttackerBlueprint from) {
                return from.getId();
            }
        });

        Map<Integer,TowerBlueprint> idToDefenderBlueprintMap = Maps.uniqueIndex(defenderBlueprints, new Function<TowerBlueprint, Integer>() {
            public Integer apply(TowerBlueprint from) {
                return from.getId();
            }
        });

        Map<Integer,ConstructionSite> idToConstructionSiteMap = Maps.uniqueIndex(dungeonBlueprint.getConstructionSites(), new Function<ConstructionSite, Integer>() {
            public Integer apply(ConstructionSite from) {
                return from.getId();
            }
        });

        Map<Integer,DungeonNode> checkPointToDungeonNodeMap = Maps.uniqueIndex(dungeonBlueprint.getDungeonNodes(), new Function<DungeonNode, Integer>() {
            public Integer apply(DungeonNode from) {
                return Integer.valueOf(from.getCheckPoint());
            }
        });
        checkPointToDungeonNodeMap = normalizeDungeonNodes(checkPointToDungeonNodeMap);

        AttackForcePattern attackForcePattern = attackForcePatternRepository.findById(fight.getAttackForce().getAttackForcePatternId());
        IdGenerator idGenerator = new IdGenerator();

        List<AttackerSpawn> attackerSpawns = new AttackerSpawnBuilder(
                fight.getAttackForce(),attackForcePattern,idToAttackerBlueprintMap, checkPointToDungeonNodeMap, idGenerator).build();

        List<DefenderSpawn> defenderSpawns = new DefenderSpawnBuilder(
                fight.getDungeon(), idToDefenderBlueprintMap, idToConstructionSiteMap, idGenerator).build();


        return new GameState(attackerSpawns, defenderSpawns, checkPointToDungeonNodeMap);

    }

    /**
     * If the DungeonBlueprint is missing a checkPoint we need to normalize this
     */
    private Map<Integer, DungeonNode> normalizeDungeonNodes(Map<Integer, DungeonNode> map){

        Map<Integer,DungeonNode> normalizedMap = new HashMap<Integer, DungeonNode>();

        int loopIndex = 0;
        int normalizedIndex = 0;
        while(map.size() > normalizedIndex){
            Integer currentKey = Integer.valueOf(loopIndex);
            DungeonNode node = map.get(currentKey);
            if(node != null){
                normalizedMap.put(normalizedIndex, node);
                normalizedIndex ++;
            }
            loopIndex++;
        }

        return normalizedMap;
    }
}
