package de.ballaci.jpa.repositories;

import de.ballaci.jpa.domain.ActorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Armand.Ballaci
 */

@Transactional(readOnly = true)
@RepositoryRestResource(collectionResourceRel = "actorinfo", path = "actorinfo")
public interface ActorInfoViewRepository extends JpaRepository<ActorInfo, Long> {

    @Query("Select a from ActorInfo a where a.lastName =:name")
    List<ActorInfo> findByActorsLastName(@Param("name") String name);
}
