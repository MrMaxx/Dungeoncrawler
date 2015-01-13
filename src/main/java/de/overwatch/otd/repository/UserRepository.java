package de.overwatch.otd.repository;


import de.overwatch.otd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED)
public interface UserRepository extends JpaRepository<User, Integer> {

}
