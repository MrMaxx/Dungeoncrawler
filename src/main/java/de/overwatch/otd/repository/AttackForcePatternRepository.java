package de.overwatch.otd.repository;


import de.overwatch.otd.domain.User;
import de.overwatch.otd.domain.attack.AttackForcePattern;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED)
public interface AttackForcePatternRepository extends JpaRepository<AttackForcePattern, Integer> {
}
