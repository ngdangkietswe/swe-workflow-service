package dev.ngdangkietswe.sweworkflowservice.grpc.server;

import dev.ngdangkietswe.sweprotobufshared.common.protobuf.IdReq;
import dev.ngdangkietswe.sweprotobufshared.proto.common.GrpcUtil;
import dev.ngdangkietswe.sweprotobufshared.proto.common.IGrpcServer;
import dev.ngdangkietswe.sweprotobufshared.proto.security.SweGrpcServerInterceptor;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetListWorkflowReq;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetListWorkflowResp;
import dev.ngdangkietswe.sweprotobufshared.workflow.GetWorkflowResp;
import dev.ngdangkietswe.sweprotobufshared.workflow.WorkflowServiceGrpc;
import dev.ngdangkietswe.sweworkflowservice.grpc.service.IWorkflowGrpcService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

/**
 * @author ngdangkietswe
 * @since 5/11/2025
 */

@GRpcService(interceptors = SweGrpcServerInterceptor.class)
@RequiredArgsConstructor
public class WorkflowGrpcServer extends WorkflowServiceGrpc.WorkflowServiceImplBase {

    private final IWorkflowGrpcService workflowGrpcService;

    @Override
    public void getWorkflow(IdReq request, StreamObserver<GetWorkflowResp> responseObserver) {
        IGrpcServer.execute(
                request,
                responseObserver,
                workflowGrpcService::getWorkflow,
                exception -> GetWorkflowResp.newBuilder()
                        .setError(GrpcUtil.asError(exception))
                        .build()
        );
    }

    @Override
    public void getListWorkflow(GetListWorkflowReq request, StreamObserver<GetListWorkflowResp> responseObserver) {
        IGrpcServer.execute(
                request,
                responseObserver,
                workflowGrpcService::getListWorkflow,
                exception -> GetListWorkflowResp.newBuilder()
                        .setError(GrpcUtil.asError(exception))
                        .build()
        );
    }
}
