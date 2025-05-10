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
@Table(name = "transition")
public class TransitionEntity extends BaseEntity {

    @JoinColumn(
            name = "workflow_id",
            referencedColumnName = "id")
    @ManyToOne(
            targetEntity = WorkflowEntity.class,
            fetch = FetchType.LAZY)
    private WorkflowEntity workflow;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @JoinColumn(
            name = "source_state_id",
            referencedColumnName = "id")
    @ManyToOne(
            targetEntity = StateEntity.class,
            fetch = FetchType.LAZY)
    private StateEntity sourceState;

    @JoinColumn(
            name = "target_state_id",
            referencedColumnName = "id")
    @ManyToOne(
            targetEntity = StateEntity.class,
            fetch = FetchType.LAZY)
    private StateEntity targetState;

    @Column
    private String conditionExpr;
}
