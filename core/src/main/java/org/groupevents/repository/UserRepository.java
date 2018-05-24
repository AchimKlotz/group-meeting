package org.groupevents.repository;

import java.util.Optional;

import org.groupevents.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByUserName(String userName);

	Optional<User> findByEMailAddress(String eMailAddress);

	Optional<User> findByActivationKey(String activationKey);

}
