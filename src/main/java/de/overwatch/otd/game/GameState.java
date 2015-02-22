package de.overwatch.otd.game;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
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
    private List<Attacker> deadAttackers = new LinkedList<Attacker>();
    private List<Turret> turrets = new LinkedList<Turret>();

    private Map<Integer, DungeonNode> checkPointToDungeonNodeMap;

    ListMultimap<Integer, GameEvent> tickToEventsMap = ArrayListMultimap.create();

    public GameState(List<AttackerSpawn> attackerSpawnes, List<DefenderSpawn> defenderSpawnes, Map<Integer, DungeonNode> checkPointToDungeonNodeMap) {
        this.attackerSpawnes = attackerSpawnes;
        this.defenderSpawnes = defenderSpawnes;
        this.checkPointToDungeonNodeMap = checkPointToDungeonNodeMap;
    }

    public void addEvent(Integer tickInMilliSeconds, GameEvent gameEvent) {
        tickToEventsMap.put(tickInMilliSeconds, gameEvent);
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

    public List<Attacker> getDeadAttackers() {
        return deadAttackers;
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

    public ListMultimap<Integer, GameEvent> getTickToEventsMap() {
        return tickToEventsMap;
    }

    public void increaseAttackerScore(int delta) {
        this.attackerScore = attackerScore + delta;
    }

}
