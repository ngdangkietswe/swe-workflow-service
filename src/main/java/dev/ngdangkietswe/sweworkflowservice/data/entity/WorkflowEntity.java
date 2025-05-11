package dev.ngdangkietswe.sweworkflowservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ngdangkietswe
 * @since 5/10/2025
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "workflow")
public class WorkflowEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer module;

    @Column
    private boolean is_default;

    @OneToMany(mappedBy = "workflow")
    private Set<StateEntity> states = new HashSet<>();

    @OneToMany(mappedBy = "workflow")
    private Set<TransitionEntity> transitions = new HashSet<>();
}
