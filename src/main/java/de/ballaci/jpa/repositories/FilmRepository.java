package de.ballaci.jpa.repositories;

import de.ballaci.jpa.domain.Film;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Armand.Ballaci
 */

@RepositoryRestResource(collectionResourceRel = "film", path = "film")
public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {
}
