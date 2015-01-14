package de.overwatch.otd.job;



import de.overwatch.otd.service.FightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutstandingFightsJob {


    private static final Logger LOGGER = Logger.getLogger(OutstandingFightsJob.class);

    @Autowired
    private FightService fightService;

    @Scheduled(fixedDelay = 30000)  // (cron="0/30 * * * * *")
    public void executeJob(){

        LOGGER.info("executing OutstandingFightsJob");
        fightService.processOutstandingFights();
    }

}
