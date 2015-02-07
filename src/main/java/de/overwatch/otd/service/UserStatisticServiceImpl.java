package de.overwatch.otd.service;

import de.overwatch.otd.repository.CustomFightRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserStatisticServiceImpl implements UserStatisticService{

    private static final Logger LOGGER = Logger.getLogger(UserStatisticServiceImpl.class);

    @Autowired
    private CustomFightRepository customFightRepository;
    

    @Override
    public UserStatistic getUserStatiStatistic(Integer userId) {
        return customFightRepository.getUserStatistic(userId);
    }
}
