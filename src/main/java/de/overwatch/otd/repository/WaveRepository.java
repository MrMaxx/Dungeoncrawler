package de.overwatch.otd.repository;


import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.domain.attack.Wave;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
public interface WaveRepository extends JpaRepository<Wave, Integer> {


    @Query("select w from Wave w where w.attackForce.id = :attackForceId and w.attackForce.user.id = :userId")
    List<Wave> findByAttackForceIdAndUserId(@Param("attackForceId") Integer attackForceId, @Param("userId") Integer userId);

    @Query("select w from Wave w where w.attackForce.id = :attackForceId and w.attackForce.user.id = :userId and w.id = :id")
    Wave findByIdAndAttackForceIdAndUserId(@Param("id") Integer id, @Param("attackForceId") Integer attackForceId, @Param("userId") Integer userId);

}
