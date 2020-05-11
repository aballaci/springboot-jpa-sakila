package de.ballaci.jpa.repositories;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Optional;

/**
 * Interface for Read-Only Query Repositories
 *
 * @param <T> the Domain Entity Type
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T extends Serializable> extends Repository<T, ID> {

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<T> iterable);

    Iterable<T> findAllById(Sort sort);

    Page<T> findAllBy(Pageable pageable);

    long count();

    boolean existsById();
}
