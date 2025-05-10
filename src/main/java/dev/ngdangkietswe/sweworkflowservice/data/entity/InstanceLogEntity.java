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
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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
@Table(name = "instance_log")
public class InstanceLogEntity extends BaseEntity {

    @JoinColumn(
            name = "instance_id",
            referencedColumnName = "id")
    @ManyToOne(
            targetEntity = InstanceEntity.class,
            fetch = FetchType.LAZY)
    private InstanceEntity instance;

    @JoinColumn(
            name = "transition_id",
            referencedColumnName = "id")
    @ManyToOne(
            targetEntity = StateEntity.class,
            fetch = FetchType.LAZY)
    private TransitionEntity transition;

    @JoinColumn(
            name = "performed_by",
            referencedColumnName = "id")
    @ManyToOne(
            targetEntity = CdcAuthUserEntity.class,
            fetch = FetchType.LAZY)
    private CdcAuthUserEntity performedBy;

    @CreationTimestamp
    @Column(
            name = "performed_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp performedAt;

    @Column
    private String comment;
}
