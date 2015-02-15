package de.overwatch.otd.repository;


import de.overwatch.otd.domain.defend.Dungeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
public interface DungeonRepository extends JpaRepository<Dungeon, Integer> {


    @Query("select d from Dungeon d left join fetch d.user")
    List<Dungeon> findAllWithUser();

    @Query("select d from Dungeon d where d.user.id = :userId")
    List<Dungeon> findByUserId(@Param("userId") Integer userId);

    @Query("select d from Dungeon d where d.user.id = :userId and d.id = :id")
    Dungeon findByIdAndUserId(@Param("id") Integer id, @Param("userId") Integer userId);

}
