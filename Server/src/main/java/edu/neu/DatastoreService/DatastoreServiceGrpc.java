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
    comments = "Source: DatastoreService.proto")
public final class DatastoreServiceGrpc {

  private DatastoreServiceGrpc() {}

  public static final String SERVICE_NAME = "DatastoreService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> getAvailableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "available",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> getAvailableMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> getAvailableMethod;
    if ((getAvailableMethod = DatastoreServiceGrpc.getAvailableMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getAvailableMethod = DatastoreServiceGrpc.getAvailableMethod) == null) {
          DatastoreServiceGrpc.getAvailableMethod = getAvailableMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "available"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("available"))
                  .build();
          }
        }
     }
     return getAvailableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> getOperateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "operate",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> getOperateMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> getOperateMethod;
    if ((getOperateMethod = DatastoreServiceGrpc.getOperateMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getOperateMethod = DatastoreServiceGrpc.getOperateMethod) == null) {
          DatastoreServiceGrpc.getOperateMethod = getOperateMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "operate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("operate"))
                  .build();
          }
        }
     }
     return getOperateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse> getUpdateMethod;
    if ((getUpdateMethod = DatastoreServiceGrpc.getUpdateMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getUpdateMethod = DatastoreServiceGrpc.getUpdateMethod) == null) {
          DatastoreServiceGrpc.getUpdateMethod = getUpdateMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("update"))
                  .build();
          }
        }
     }
     return getUpdateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DatastoreServiceStub newStub(io.grpc.Channel channel) {
    return new DatastoreServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DatastoreServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DatastoreServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DatastoreServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DatastoreServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class DatastoreServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void available(edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAvailableMethod(), responseObserver);
    }

    /**
     */
    public void operate(edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOperateMethod(), responseObserver);
    }

    /**
     */
    public void update(edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAvailableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse>(
                  this, METHODID_AVAILABLE)))
          .addMethod(
            getOperateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse>(
                  this, METHODID_OPERATE)))
          .addMethod(
            getUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse>(
                  this, METHODID_UPDATE)))
          .build();
    }
  }

  /**
   */
  public static final class DatastoreServiceStub extends io.grpc.stub.AbstractStub<DatastoreServiceStub> {
    private DatastoreServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatastoreServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatastoreServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatastoreServiceStub(channel, callOptions);
    }

    /**
     */
    public void available(edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAvailableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void operate(edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOperateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DatastoreServiceBlockingStub extends io.grpc.stub.AbstractStub<DatastoreServiceBlockingStub> {
    private DatastoreServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatastoreServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatastoreServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatastoreServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse available(edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest request) {
      return blockingUnaryCall(
          getChannel(), getAvailableMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse operate(edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest request) {
      return blockingUnaryCall(
          getChannel(), getOperateMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse update(edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DatastoreServiceFutureStub extends io.grpc.stub.AbstractStub<DatastoreServiceFutureStub> {
    private DatastoreServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DatastoreServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatastoreServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DatastoreServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> available(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAvailableMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse> operate(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOperateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse> update(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AVAILABLE = 0;
  private static final int METHODID_OPERATE = 1;
  private static final int METHODID_UPDATE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DatastoreServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DatastoreServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AVAILABLE:
          serviceImpl.available((edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse>) responseObserver);
          break;
        case METHODID_OPERATE:
          serviceImpl.operate((edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.OperateResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.DatastoreResponse>) responseObserver);
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

  private static abstract class DatastoreServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DatastoreServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.neu.DatastoreService.DatastoreServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DatastoreService");
    }
  }

  private static final class DatastoreServiceFileDescriptorSupplier
      extends DatastoreServiceBaseDescriptorSupplier {
    DatastoreServiceFileDescriptorSupplier() {}
  }

  private static final class DatastoreServiceMethodDescriptorSupplier
      extends DatastoreServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DatastoreServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (DatastoreServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DatastoreServiceFileDescriptorSupplier())
              .addMethod(getAvailableMethod())
              .addMethod(getOperateMethod())
              .addMethod(getUpdateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
