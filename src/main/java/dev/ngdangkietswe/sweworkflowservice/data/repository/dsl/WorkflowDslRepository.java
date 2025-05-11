package dev.ngdangkietswe.sweworkflowservice.data.repository.dsl;

import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.ngdangkietswe.swejavacommonshared.domain.Page;
import dev.ngdangkietswe.sweprotobufshared.common.protobuf.Module;
import dev.ngdangkietswe.sweprotobufshared.common.protobuf.Pageable;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetListWorkflowReq;
import dev.ngdangkietswe.sweworkflowservice.data.entity.QWorkflowEntity;
import dev.ngdangkietswe.sweworkflowservice.data.entity.WorkflowEntity;
import dev.ngdangkietswe.sweworkflowservice.data.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@Repository
@RequiredArgsConstructor
public class WorkflowDslRepository {

    public static final Map<String, ComparableExpressionBase<?>> WORKFLOW_ORDER_MAP = Map.of(
            "name", QWorkflowEntity.workflowEntity.name,
            "code", QWorkflowEntity.workflowEntity.code,
            "created_at", QWorkflowEntity.workflowEntity.createdAt,
            "updated_at", QWorkflowEntity.workflowEntity.updatedAt
    );

    private final JPAQueryFactory factory;
    private final QWorkflowEntity qWorkflow = QWorkflowEntity.workflowEntity;

    @SuppressWarnings("unchecked")
    public Page<WorkflowEntity> findAllByReq(GetListWorkflowReq req, Pageable pageable) {
        JPAQuery<WorkflowEntity> query = (JPAQuery<WorkflowEntity>) factory.from(qWorkflow);

        if (req.hasModule() && req.getModule() != Module.MODULE_UNSPECIFIED) {
            query.where(qWorkflow.module.eq(req.getModule().getNumber()));
        }

        if (StringUtils.isNotEmpty(req.getSearch())) {
            query.where(qWorkflow.name.containsIgnoreCase(req.getSearch())
                    .or(qWorkflow.code.containsIgnoreCase(req.getSearch())));
        }

        return PageUtils.paginate(
                pageable,
                query,
                qWorkflow,
                WORKFLOW_ORDER_MAP,
                qWorkflow.updatedAt,
                qWorkflow);
    }
}
