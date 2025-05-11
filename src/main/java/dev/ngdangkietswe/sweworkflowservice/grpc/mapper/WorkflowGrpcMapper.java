package dev.ngdangkietswe.sweworkflowservice.grpc.mapper;

import dev.ngdangkietswe.sweprotobufshared.common.protobuf.Module;
import dev.ngdangkietswe.sweprotobufshared.proto.common.GrpcMapper;
import dev.ngdangkietswe.sweprotobufshared.workflow.protobuf.Workflow;
import dev.ngdangkietswe.sweworkflowservice.data.entity.WorkflowEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@Component
@RequiredArgsConstructor
public class WorkflowGrpcMapper extends GrpcMapper<WorkflowEntity, Workflow, Workflow.Builder> {

    @Override
    public Workflow.Builder toGrpcBuilder(WorkflowEntity from) {
        return Workflow.newBuilder()
                .setId(from.getId().toString())
                .setName(from.getName())
                .setCode(from.getCode())
                .setModule(Module.forNumber(from.getModule()))
                .setIsDefault(from.is_default());
    }

    @Override
    public Workflow toGrpcMessage(WorkflowEntity from) {
        return toGrpcBuilder(from).build();
    }

    @Override
    public List<Workflow> toGrpcMessages(List<WorkflowEntity> from) {
        return super.toGrpcMessages(from);
    }
}
