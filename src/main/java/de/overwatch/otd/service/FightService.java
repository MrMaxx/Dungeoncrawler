package de.overwatch.otd.service;

import java.util.List;

public interface FightService {

    void processOutstandingFights();

    /** Todo: remove...only temporary for the MVP Prototype */
    PublicFight createFightAgainst(Integer attackingUserId, Integer defendingUserId);

    PublicFight createFight(Integer dungeonId, Integer attackForceId);
    List<PublicFight> getPublicFights(Integer userId);
    PublicFight getPublicFight(Integer id, Integer userId);

}
