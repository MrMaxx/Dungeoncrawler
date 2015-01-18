package de.overwatch.otd.game;


import de.overwatch.otd.domain.defend.DungeonNode;
import de.overwatch.otd.domain.defend.Tower;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.AttackerSpawn;
import de.overwatch.otd.game.model.DefenderSpawn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameState {

    private List<AttackerSpawn> attackerSpawnes;
    private List<DefenderSpawn> defenderSpawnes;
    private List<Attacker> attackers = new LinkedList<Attacker>();
    private List<Tower> towers = new LinkedList<Tower>();

    private Map<Integer, DungeonNode> checkPointToDungeonNodeMap;

    public GameState(List<AttackerSpawn> attackerSpawnes, List<DefenderSpawn> defenderSpawnes, Map<Integer, DungeonNode> checkPointToDungeonNodeMap) {
        this.attackerSpawnes = attackerSpawnes;
        this.defenderSpawnes = defenderSpawnes;
        this.checkPointToDungeonNodeMap = checkPointToDungeonNodeMap;
    }

    public List<AttackerSpawn> getAttackerSpawnes() {
        return attackerSpawnes;
    }

    public List<DefenderSpawn> getDefenderSpawnes() {
        return defenderSpawnes;
    }

    public List<Attacker> getAttackers() {
        return attackers;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public Map<Integer, DungeonNode> getCheckPointToDungeonNodeMap() {
        return checkPointToDungeonNodeMap;
    }
}
