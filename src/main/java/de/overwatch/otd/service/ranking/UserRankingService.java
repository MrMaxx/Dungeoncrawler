package de.overwatch.otd.service.ranking;


import de.overwatch.otd.domain.User;

import java.util.List;

public interface UserRankingService {

    void addUser(User user);

    void changeScore(Integer userId, int newScore);

    List<UserWithRank> getUserRanking();

}
