package dev.ngdangkietswe.sweworkflowservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "instance")
public class InstanceEntity extends BaseEntity {

    @JoinColumn(
            name = "workflow_id",
            referencedColumnName = "id")
    @ManyToOne(
            targetEntity = WorkflowEntity.class,
            fetch = FetchType.LAZY)
    private WorkflowEntity workflow;

    @Column(nullable = false)
    private Integer module;

    @Column(nullable = false)
    private UUID entityId;

    @JoinColumn(
            name = "current_state_id",
            referencedColumnName = "id")
    @ManyToOne(
            targetEntity = StateEntity.class,
            fetch = FetchType.LAZY)
    private StateEntity state;
}
