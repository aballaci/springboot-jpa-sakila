package de.ballaci.jpa.repositories;

import de.ballaci.jpa.domain.Actor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Accessing JPA Data with REST
 * https://spring.io/guides/gs/accessing-data-rest/
 */
@RepositoryRestResource(collectionResourceRel = "actors", path = "actors")
public interface ActorRepository extends PagingAndSortingRepository<Actor, Long> {
    /**
     * Custom query: Custom queries can be listed in:
     * http://localhost:8080/actors/search
     * @param name
     * @return a list of Actors filtered by lastname
     */
    List<Actor> findByLastName(@Param("name") String name);
}
