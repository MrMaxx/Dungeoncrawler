package de.overwatch.otd.game;


import de.overwatch.otd.domain.defend.DungeonNode;
import de.overwatch.otd.domain.defend.Tower;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.AttackerSpawn;
import de.overwatch.otd.game.model.DefenderSpawn;
import de.overwatch.otd.game.model.Turret;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameState {

    private int attackerScore;

    private List<AttackerSpawn> attackerSpawnes;
    private List<DefenderSpawn> defenderSpawnes;
    private List<Attacker> attackers = new LinkedList<Attacker>();
    private List<Turret> turrets = new LinkedList<Turret>();

    private Map<Integer, DungeonNode> checkPointToDungeonNodeMap;

    List<GameEvent> events = new LinkedList<GameEvent>();

    public GameState(List<AttackerSpawn> attackerSpawnes, List<DefenderSpawn> defenderSpawnes, Map<Integer, DungeonNode> checkPointToDungeonNodeMap) {
        this.attackerSpawnes = attackerSpawnes;
        this.defenderSpawnes = defenderSpawnes;
        this.checkPointToDungeonNodeMap = checkPointToDungeonNodeMap;
    }

    public List<GameEvent> getEvents() {
        return events;
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

    public List<Turret> getTurrets() {
        return turrets;
    }

    public Map<Integer, DungeonNode> getCheckPointToDungeonNodeMap() {
        return checkPointToDungeonNodeMap;
    }

    public int getAttackerScore() {
        return attackerScore;
    }

    public void increaseAttackerScore(int delta) {
        this.attackerScore = attackerScore + delta;
    }

}
