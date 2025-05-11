package dev.ngdangkietswe.sweworkflowservice.data.repository.jpa;

import dev.ngdangkietswe.sweworkflowservice.data.entity.WorkflowEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@Repository
public interface WorkflowRepository extends IBaseRepository<WorkflowEntity> {

    @Query("SELECT w FROM WorkflowEntity w " +
           "LEFT JOIN FETCH w.states s " +
           "LEFT JOIN FETCH w.transitions t WHERE w.id = :id")
    Optional<WorkflowEntity> findByIdFetchStatesAndTransitions(UUID id);
}
