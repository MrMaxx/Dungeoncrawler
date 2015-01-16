package de.overwatch.otd.repository;


import de.overwatch.otd.domain.attack.AttackForcePattern;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(propagation= Propagation.REQUIRED)
public interface AttackForcePatternRepository extends JpaRepository<AttackForcePattern, Integer> {


    @EntityGraph(value = "graph.attackForcePattern.complete", type = EntityGraph.EntityGraphType.LOAD)
    @Query
    public AttackForcePattern findById(Integer id);

    @EntityGraph(value = "graph.attackForcePattern.complete", type = EntityGraph.EntityGraphType.LOAD)
    @Query
    public List<AttackForcePattern> findAllByOrderByIdAsc();

}
