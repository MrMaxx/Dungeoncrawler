package de.overwatch.otd.repository;


import de.overwatch.otd.service.UserStatistic;

import java.util.List;

public interface CustomFightRepository {


    UserStatistic getUserStatistic(Integer userId);


}
