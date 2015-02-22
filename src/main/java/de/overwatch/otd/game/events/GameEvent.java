package de.overwatch.otd.game.events;

public abstract class GameEvent {

    public static final String EVENT_TYPE_ATTACKERSPAWN = "ATTACKER_SPAWN";
    public static final String EVENT_TYPE_DEFENDERSPAWN = "DEFENDER_SPAWN";
    public static final String EVENT_TYPE_ATTACKERSUCCEEDED = "ATTACKER_SUCCEEDED";
    public static final String EVENT_TYPE_ATTACKERDIED = "ATTACKER_DIED";
    public static final String EVENT_TYPE_TOWERTARGETSATTACKER = "TOWER_NEW_TARGET";
    public static final String EVENT_TYPE_TOWEREFFECTSATTACKER = "TOWER_EFFECTS_TARGET";
    public static final String EVENT_TYPE_TOWERLOSTTARGET = "TOWER_LOST_TARGET";
    public static final String EVENT_TYPE_MOVETO = "MOVETO";



    // Todo: verify if Jackson renders json by looking at fields or getter, then perhaps remove this field
    private String type;
    private int elementId;

    public GameEvent(int elementId){
        type = getType();
        this.elementId = elementId;
    }

    public abstract String getType();
    public int getElementId(){return elementId;}
}
