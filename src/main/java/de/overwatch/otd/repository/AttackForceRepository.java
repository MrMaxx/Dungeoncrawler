package de.overwatch.otd.repository;


import de.overwatch.otd.domain.attack.AttackForce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
public interface AttackForceRepository extends JpaRepository<AttackForce, Integer> {


    //duplicates results @EntityGraph(value = "graph.attackForce.withWaves", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select af from AttackForce af where af.user.id = :userId")
    List<AttackForce> findByUserId(@Param("userId") Integer userId);

    //@EntityGraph(value = "graph.attackForce.withWaves", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select af from AttackForce af where af.user.id = :userId and af.id = :id")
    AttackForce findByIdAndUserId(@Param("id") Integer id, @Param("userId") Integer userId);

}
