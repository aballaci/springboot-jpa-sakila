package de.ballaci.jpa.repositories;

import de.ballaci.jpa.domain.Actor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "actors", path = "actors")
public interface ActorRepository extends PagingAndSortingRepository<Actor, Long> {
}
