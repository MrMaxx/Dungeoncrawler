package de.overwatch.otd.job;



import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import de.overwatch.otd.domain.User;
import de.overwatch.otd.domain.defend.Dungeon;
import de.overwatch.otd.repository.DungeonRepository;
import de.overwatch.otd.repository.UserRepository;
import de.overwatch.otd.service.FightService;
import de.overwatch.otd.service.ranking.UserRankingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserRankingBuilderJob implements InitializingBean{


    private static final Logger LOGGER = Logger.getLogger(UserRankingBuilderJob.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRankingService userRankingService;

    @Autowired
    private DungeonRepository dungeonRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Starting to ramp up UserRanking Service Cache.");

        List<User> allUsers = userRepository.findAll();
        List<Dungeon> allDungeons = dungeonRepository.findAllWithUser();

        Map<Integer, Dungeon> userIdIdToDungeonMap = Maps.uniqueIndex(allDungeons, new Function<Dungeon, Integer>() {
            @Override
            public Integer apply(Dungeon dungeon) {
                return dungeon.getUser().getId();
            }
        });

        for(User user : allUsers){
            Dungeon dungeon = userIdIdToDungeonMap.get(user.getId());
            userRankingService.addUser(user, (dungeon != null));

        }
        LOGGER.info("Added "+allUsers.size()+" Users to UserRanking Service Cache.");
        LOGGER.info("Finished to ramp up UserRanking Service Cache.");
    }
}
