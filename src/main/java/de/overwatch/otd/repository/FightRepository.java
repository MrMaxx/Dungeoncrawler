package de.overwatch.otd.repository;


import de.overwatch.otd.domain.Fight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
public interface FightRepository extends JpaRepository<Fight, Integer> {



    List<Fight> findByFightState(Fight.FightState fightState);

}
