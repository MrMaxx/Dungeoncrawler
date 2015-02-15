package de.overwatch.otd.repository;


import de.overwatch.otd.domain.Fight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
public interface FightRepository extends JpaRepository<Fight, Integer> {


    List<Fight> findByFightState(Fight.FightState fightState);

    @Query("select f from Fight f where f.dungeon.id = :userId OR f.attackForce.user.id = :userId ORDER BY f.id DESC")
    List<Fight> findByUserId(@Param("userId") Integer userId);

    @Query("select f from Fight f where f.id = :id AND (f.dungeon.id = :userId OR f.attackForce.user.id = :userId)")
    Fight findByIdAndUserId(@Param("id") Integer id, @Param("userId") Integer userId);

}
