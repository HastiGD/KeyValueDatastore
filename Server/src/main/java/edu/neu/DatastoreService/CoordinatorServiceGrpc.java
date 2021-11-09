package edu.neu.DatastoreService;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: CoordinatorService.proto")
public final class CoordinatorServiceGrpc {

  private CoordinatorServiceGrpc() {}

  public static final String SERVICE_NAME = "CoordinatorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest,
      edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse> getOperateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "operate",
      requestType = edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest.class,
      responseType = edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest,
      edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse> getOperateMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest, edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse> getOperateMethod;
    if ((getOperateMethod = CoordinatorServiceGrpc.getOperateMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getOperateMethod = CoordinatorServiceGrpc.getOperateMethod) == null) {
          CoordinatorServiceGrpc.getOperateMethod = getOperateMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest, edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CoordinatorService", "operate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("operate"))
                  .build();
          }
        }
     }
     return getOperateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CoordinatorServiceStub newStub(io.grpc.Channel channel) {
    return new CoordinatorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CoordinatorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CoordinatorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CoordinatorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CoordinatorServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CoordinatorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void operate(edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOperateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOperateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest,
                edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse>(
                  this, METHODID_OPERATE)))
          .build();
    }
  }

  /**
   */
  public static final class CoordinatorServiceStub extends io.grpc.stub.AbstractStub<CoordinatorServiceStub> {
    private CoordinatorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CoordinatorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoordinatorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceStub(channel, callOptions);
    }

    /**
     */
    public void operate(edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOperateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CoordinatorServiceBlockingStub extends io.grpc.stub.AbstractStub<CoordinatorServiceBlockingStub> {
    private CoordinatorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CoordinatorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoordinatorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse operate(edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest request) {
      return blockingUnaryCall(
          getChannel(), getOperateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CoordinatorServiceFutureStub extends io.grpc.stub.AbstractStub<CoordinatorServiceFutureStub> {
    private CoordinatorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CoordinatorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoordinatorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse> operate(
        edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOperateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_OPERATE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CoordinatorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CoordinatorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_OPERATE:
          serviceImpl.operate((edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.CoordinatorServiceOuterClass.OperateResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CoordinatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CoordinatorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.neu.DatastoreService.CoordinatorServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CoordinatorService");
    }
  }

  private static final class CoordinatorServiceFileDescriptorSupplier
      extends CoordinatorServiceBaseDescriptorSupplier {
    CoordinatorServiceFileDescriptorSupplier() {}
  }

  private static final class CoordinatorServiceMethodDescriptorSupplier
      extends CoordinatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CoordinatorServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CoordinatorServiceFileDescriptorSupplier())
              .addMethod(getOperateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
