package de.overwatch.otd.game;


import de.overwatch.otd.domain.attack.AttackerBlueprint;
import de.overwatch.otd.domain.defend.ConstructionSite;
import de.overwatch.otd.domain.defend.DungeonNode;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.game.model.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GameTest {



    public GameState defaultGameState(){

        Map<Integer,DungeonNode> checkPointToDungeonNodeMap = new HashMap<Integer, DungeonNode>();

        DungeonNode node1 = new DungeonNode();
        node1.setX(80);
        node1.setY(80);
        node1.setCheckPoint(1);
        node1.setId(1);

        DungeonNode node2 = new DungeonNode();
        node2.setX(800);
        node2.setY(80);
        node2.setCheckPoint(2);
        node2.setId(2);

        DungeonNode node3 = new DungeonNode();
        node3.setX(800);
        node3.setY(800);
        node3.setCheckPoint(3);
        node3.setId(3);

        checkPointToDungeonNodeMap.put(0, node1);
        checkPointToDungeonNodeMap.put(1, node2);
        checkPointToDungeonNodeMap.put(2, node3);

        GameState gameState = new GameState(
                                            new LinkedList<AttackerSpawn>(),
                                            new LinkedList<DefenderSpawn>(),
                                            checkPointToDungeonNodeMap);

        return gameState;
    }

    public AttackerBlueprint defaultAttackerBlueprint(){
        AttackerBlueprint blueprint = new AttackerBlueprint();
        blueprint.setId(1);
        blueprint.setMaxHealth(1000);
        blueprint.setPrice(100);
        blueprint.setSpeed(100);
        blueprint.setType("DEFAULT_ATTACKER");
        return blueprint;
    }

    public TowerBlueprint defaultTowerBlueprint(){
        TowerBlueprint blueprint = new TowerBlueprint();
        blueprint.setId(1);
        blueprint.setType("DEFAULT_TOWER");
        blueprint.setPrice(100);
        blueprint.setDamage(100);
        blueprint.setRange(100);
        blueprint.setTimeToReload(10);
        return blueprint;
    }

    public ConstructionSite createConstructionSite(Integer id, int x, int y){
        ConstructionSite constructionSite = new ConstructionSite();
        constructionSite.setId(1);
        constructionSite.setX(x);
        constructionSite.setY(y);
        return constructionSite;
    }

    public Turret createTurret(Integer id, TowerBlueprint towerBlueprint, ConstructionSite constructionSite){
        Turret turret = new Turret(id, towerBlueprint, constructionSite);
        turret.setLastShot(0);
        return turret;
    }

    public Attacker createAttacker(int id,
                                   AttackerBlueprint attackerBlueprint,
                                   Coordinate coordinate,
                                   NodeVisit firstNodeVisit){
        Attacker attacker = new Attacker(id, attackerBlueprint);
        attacker.moveTo(coordinate);
        attacker.setLastDungeonNodeVisit(firstNodeVisit,0);
        return attacker;
    }




}
