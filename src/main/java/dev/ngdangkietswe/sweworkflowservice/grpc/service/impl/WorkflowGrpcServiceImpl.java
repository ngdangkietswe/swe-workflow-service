package dev.ngdangkietswe.sweworkflowservice.grpc.service.impl;

import dev.ngdangkietswe.sweprotobufshared.common.protobuf.IdReq;
import dev.ngdangkietswe.sweprotobufshared.proto.common.GrpcUtil;
import dev.ngdangkietswe.sweprotobufshared.proto.domain.SweGrpcPrincipal;
import dev.ngdangkietswe.sweprotobufshared.proto.exception.GrpcNotFoundException;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetListWorkflowReq;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetListWorkflowResp;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetWorkflowResp;
import dev.ngdangkietswe.sweworkflowservice.data.entity.WorkflowEntity;
import dev.ngdangkietswe.sweworkflowservice.data.repository.dsl.WorkflowDslRepository;
import dev.ngdangkietswe.sweworkflowservice.data.repository.jpa.WorkflowRepository;
import dev.ngdangkietswe.sweworkflowservice.grpc.mapper.StateGrpcMapper;
import dev.ngdangkietswe.sweworkflowservice.grpc.mapper.TransitionGrpcMapper;
import dev.ngdangkietswe.sweworkflowservice.grpc.mapper.WorkflowGrpcMapper;
import dev.ngdangkietswe.sweworkflowservice.grpc.service.IWorkflowGrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.UUID;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@Service
@RequiredArgsConstructor
public class WorkflowGrpcServiceImpl implements IWorkflowGrpcService {

    private final WorkflowRepository workflowRepository;
    private final WorkflowDslRepository workflowDslRepository;

    private final WorkflowGrpcMapper workflowGrpcMapper;
    private final StateGrpcMapper stateGrpcMapper;
    private final TransitionGrpcMapper transitionGrpcMapper;

    @Override
    public GetWorkflowResp getWorkflow(IdReq req, SweGrpcPrincipal principal) {
        var workflow = workflowRepository.findByIdFetchStatesAndTransitions(UUID.fromString(req.getId()))
                .orElseThrow(() -> new GrpcNotFoundException(WorkflowEntity.class, "id", req.getId()));

        var builder = workflowGrpcMapper.toGrpcBuilder(workflow);

        if (!CollectionUtils.isEmpty(workflow.getStates())) {
            builder.addAllStates(stateGrpcMapper.toGrpcMessages(workflow.getStates().stream().toList()));
        }

        if (!CollectionUtils.isEmpty(workflow.getTransitions())) {
            builder.addAllTransitions(transitionGrpcMapper.toGrpcMessages(workflow.getTransitions().stream().toList()));
        }

        return GetWorkflowResp.newBuilder()
                .setSuccess(true)
                .setWorkflow(builder.build())
                .build();
    }

    @Override
    public GetListWorkflowResp getListWorkflow(GetListWorkflowReq req, SweGrpcPrincipal principal) {
        var normalizePageable = GrpcUtil.normalize(req.getPageable());

        var data = workflowDslRepository.findAllByReq(req, normalizePageable);

        return GetListWorkflowResp.newBuilder()
                .setSuccess(true)
                .setData(GetListWorkflowResp.Data.newBuilder()
                        .addAllWorkflows(workflowGrpcMapper.toGrpcMessages(data.getItems()))
                        .setPageMetaData(GrpcUtil.asPageMetaData(normalizePageable, data.getTotalItems()))
                        .build())
                .build();
    }
}
