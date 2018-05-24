package org.groupevents.repository;

import java.util.Optional;

import org.groupevents.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GroupRepository extends JpaRepository<Group,Long>{
	Optional<Group> findByName(String name);
	Optional<Group> findByUrl(String url);



}
