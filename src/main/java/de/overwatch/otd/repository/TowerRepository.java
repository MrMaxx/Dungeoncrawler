package de.overwatch.otd.repository;


import de.overwatch.otd.domain.defend.Tower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
public interface TowerRepository extends JpaRepository<Tower, Integer> {


    @Query("select t from Tower t where t.dungeon.id = :dungeonId and t.dungeon.user.id = :userId")
    List<Tower> findByDungeonIdAndUserId(@Param("dungeonId") Integer attackForceId, @Param("userId") Integer userId);

    @Query("select t from Tower t where t.dungeon.id = :dungeonId and t.dungeon.user.id = :userId and t.id = :id")
    Tower findByIdAndDungeonIdAndUserId(@Param("id") Integer id, @Param("dungeonId") Integer attackForceId, @Param("userId") Integer userId);

}
