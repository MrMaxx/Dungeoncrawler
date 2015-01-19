package de.overwatch.otd.game.events;

public abstract class GameEvent {

    protected static final String EVENT_TYPE_ATTACKERSPAWN = "ATTACKER_SPAWN";
    protected static final String EVENT_TYPE_DEFENDERSPAWN = "DEFENDER_SPAWN";
    protected static final String EVENT_TYPE_ATTACKERSUCCEEDED = "ATTACKER_SUCCEEDED";
    protected static final String EVENT_TYPE_MOVETO = "MOVETO";



    // Todo: verify if Jackson renders json by looking at fields or getter, then perhaps remove this field
    private String type;

    public GameEvent(){
        type = getType();
    }

    public abstract String getType();

}
