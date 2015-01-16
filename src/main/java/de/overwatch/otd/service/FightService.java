package de.overwatch.otd.service;

import java.util.List;

public interface FightService {

    void processOutstandingFights();

    List<PublicFight> getPublicFights(Integer userId);
    PublicFight getPublicFight(Integer id, Integer userId);

}
