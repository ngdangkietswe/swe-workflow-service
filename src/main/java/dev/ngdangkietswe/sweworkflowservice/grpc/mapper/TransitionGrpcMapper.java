package dev.ngdangkietswe.sweworkflowservice.grpc.mapper;

import dev.ngdangkietswe.sweprotobufshared.proto.common.GrpcMapper;
import dev.ngdangkietswe.sweprotobufshared.workflow.protobuf.Transition;
import dev.ngdangkietswe.sweworkflowservice.data.entity.TransitionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@Component
public class TransitionGrpcMapper extends GrpcMapper<TransitionEntity, Transition, Transition.Builder> {

    @Override
    public Transition.Builder toGrpcBuilder(TransitionEntity from) {
        return Transition.newBuilder()
                .setId(from.getId().toString())
                .setCode(from.getCode())
                .setName(from.getName())
                .setWorkflowId(from.getWorkflow().getId().toString())
                .setSourceStateId(from.getSourceState().getId().toString())
                .setTargetStateId(from.getTargetState().getId().toString())
                .setConditionExpr(StringUtils.defaultIfEmpty(from.getConditionExpr(), StringUtils.EMPTY));
    }

    @Override
    public Transition toGrpcMessage(TransitionEntity from) {
        return toGrpcBuilder(from).build();
    }

    @Override
    public List<Transition> toGrpcMessages(List<TransitionEntity> from) {
        return super.toGrpcMessages(from);
    }
}
