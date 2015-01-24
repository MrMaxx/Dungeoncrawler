package de.overwatch.otd.repository;


import de.overwatch.otd.domain.defend.DungeonBlueprint;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional(propagation= Propagation.REQUIRED)
public interface DungeonBlueprintRepository extends JpaRepository<DungeonBlueprint, Integer> {


    @EntityGraph(value = "graph.dungeon.complete", type = EntityGraph.EntityGraphType.LOAD)
    @Query
    List<DungeonBlueprint> findAllDistinctByOrderByIdAsc();

    @EntityGraph(value = "graph.dungeon.complete", type = EntityGraph.EntityGraphType.LOAD)
    @Query
    DungeonBlueprint findById(Integer id);

}
