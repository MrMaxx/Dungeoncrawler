package de.overwatch.otd.game.util;

/**
 * Creates Ids that are unique for one Fight
 */
public class IdGenerator {

    private int currentId = 1;

    public synchronized Integer getNextId(){
        currentId ++;
        return currentId;
    }

}
