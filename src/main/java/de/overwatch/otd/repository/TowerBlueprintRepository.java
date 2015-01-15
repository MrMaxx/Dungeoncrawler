package de.overwatch.otd.repository;


import de.overwatch.otd.domain.attack.AttackerBlueprint;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED)
public interface TowerBlueprintRepository extends JpaRepository<TowerBlueprint, Integer> {

}
