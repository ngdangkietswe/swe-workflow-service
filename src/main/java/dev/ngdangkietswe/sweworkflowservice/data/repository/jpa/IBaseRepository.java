package dev.ngdangkietswe.sweworkflowservice.data.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@NoRepositoryBean
public interface IBaseRepository<E extends Serializable> extends JpaRepository<E, UUID> {
}
