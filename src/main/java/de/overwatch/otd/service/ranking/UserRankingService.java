package de.overwatch.otd.service.ranking;


import de.overwatch.otd.domain.User;

public interface UserRankingService {

    void addUser(User user);

    UserWithRank getUserWithRank(Integer userId);

    void changeScore(Integer userId, int newScore);

    PagedUserList getUserRanking(Integer userId, int pagesize);

    PagedUserList getUserPage(int page, int pagesize);

}
