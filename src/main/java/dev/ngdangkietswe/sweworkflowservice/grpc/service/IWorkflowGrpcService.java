package dev.ngdangkietswe.sweworkflowservice.grpc.service;

import dev.ngdangkietswe.sweprotobufshared.common.protobuf.IdReq;
import dev.ngdangkietswe.sweprotobufshared.proto.domain.SweGrpcPrincipal;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetListWorkflowReq;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetListWorkflowResp;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetWorkflowResp;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

public interface IWorkflowGrpcService {

    GetWorkflowResp getWorkflow(IdReq req, SweGrpcPrincipal principal);

    GetListWorkflowResp getListWorkflow(GetListWorkflowReq req, SweGrpcPrincipal principal);
}
