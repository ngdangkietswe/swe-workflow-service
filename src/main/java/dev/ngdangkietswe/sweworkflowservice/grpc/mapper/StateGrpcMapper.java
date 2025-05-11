package dev.ngdangkietswe.sweworkflowservice.grpc.mapper;

import dev.ngdangkietswe.sweprotobufshared.proto.common.GrpcMapper;
import dev.ngdangkietswe.sweprotobufshared.workflow.protobuf.State;
import dev.ngdangkietswe.sweworkflowservice.data.entity.StateEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@Component
public class StateGrpcMapper extends GrpcMapper<StateEntity, State, State.Builder> {

    @Override
    public State.Builder toGrpcBuilder(StateEntity from) {
        return State.newBuilder()
                .setId(from.getId().toString())
                .setCode(from.getCode())
                .setName(from.getName())
                .setWorkflowId(from.getWorkflow().getId().toString())
                .setHexColor(StringUtils.defaultIfEmpty(from.getHexColor(), StringUtils.EMPTY))
                .setIsInitial(from.isInitial())
                .setIsFinal(from.isFinal())
                .setOrderNo(ObjectUtils.defaultIfNull(from.getOrderNo(), 0));
    }

    @Override
    public State toGrpcMessage(StateEntity from) {
        return toGrpcBuilder(from).build();
    }

    @Override
    public List<State> toGrpcMessages(List<StateEntity> from) {
        return super.toGrpcMessages(from);
    }
}
