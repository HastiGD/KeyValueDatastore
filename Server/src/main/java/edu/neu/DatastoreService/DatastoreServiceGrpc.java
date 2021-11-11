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
  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "put",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getPutMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getPutMethod;
    if ((getPutMethod = DatastoreServiceGrpc.getPutMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getPutMethod = DatastoreServiceGrpc.getPutMethod) == null) {
          DatastoreServiceGrpc.getPutMethod = getPutMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("put"))
                  .build();
          }
        }
     }
     return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getCoordinatorPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorPut",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getCoordinatorPutMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getCoordinatorPutMethod;
    if ((getCoordinatorPutMethod = DatastoreServiceGrpc.getCoordinatorPutMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getCoordinatorPutMethod = DatastoreServiceGrpc.getCoordinatorPutMethod) == null) {
          DatastoreServiceGrpc.getCoordinatorPutMethod = getCoordinatorPutMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "coordinatorPut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("coordinatorPut"))
                  .build();
          }
        }
     }
     return getCoordinatorPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getGetMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getGetMethod;
    if ((getGetMethod = DatastoreServiceGrpc.getGetMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getGetMethod = DatastoreServiceGrpc.getGetMethod) == null) {
          DatastoreServiceGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getDeleteMethod;
    if ((getDeleteMethod = DatastoreServiceGrpc.getDeleteMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getDeleteMethod = DatastoreServiceGrpc.getDeleteMethod) == null) {
          DatastoreServiceGrpc.getDeleteMethod = getDeleteMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("delete"))
                  .build();
          }
        }
     }
     return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getCoordinatorDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorDelete",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getCoordinatorDeleteMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getCoordinatorDeleteMethod;
    if ((getCoordinatorDeleteMethod = DatastoreServiceGrpc.getCoordinatorDeleteMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getCoordinatorDeleteMethod = DatastoreServiceGrpc.getCoordinatorDeleteMethod) == null) {
          DatastoreServiceGrpc.getCoordinatorDeleteMethod = getCoordinatorDeleteMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "coordinatorDelete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("coordinatorDelete"))
                  .build();
          }
        }
     }
     return getCoordinatorDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.Request,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getAvailableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "available",
      requestType = edu.neu.DatastoreService.DatastoreServiceOuterClass.Request.class,
      responseType = edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.Request,
      edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getAvailableMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.DatastoreServiceOuterClass.Request, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> getAvailableMethod;
    if ((getAvailableMethod = DatastoreServiceGrpc.getAvailableMethod) == null) {
      synchronized (DatastoreServiceGrpc.class) {
        if ((getAvailableMethod = DatastoreServiceGrpc.getAvailableMethod) == null) {
          DatastoreServiceGrpc.getAvailableMethod = getAvailableMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.DatastoreServiceOuterClass.Request, edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DatastoreService", "available"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DatastoreServiceMethodDescriptorSupplier("available"))
                  .build();
          }
        }
     }
     return getAvailableMethod;
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
    public void put(edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void coordinatorPut(edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorPutMethod(), responseObserver);
    }

    /**
     */
    public void get(edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void delete(edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    public void coordinatorDelete(edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorDeleteMethod(), responseObserver);
    }

    /**
     */
    public void available(edu.neu.DatastoreService.DatastoreServiceOuterClass.Request request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAvailableMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>(
                  this, METHODID_PUT)))
          .addMethod(
            getCoordinatorPutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>(
                  this, METHODID_COORDINATOR_PUT)))
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>(
                  this, METHODID_DELETE)))
          .addMethod(
            getCoordinatorDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>(
                  this, METHODID_COORDINATOR_DELETE)))
          .addMethod(
            getAvailableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.DatastoreServiceOuterClass.Request,
                edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>(
                  this, METHODID_AVAILABLE)))
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
    public void put(edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatorPut(edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatorDelete(edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void available(edu.neu.DatastoreService.DatastoreServiceOuterClass.Request request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAvailableMethod(), getCallOptions()), request, responseObserver);
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
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse put(edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest request) {
      return blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse coordinatorPut(edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse get(edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse delete(edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse coordinatorDelete(edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse available(edu.neu.DatastoreService.DatastoreServiceOuterClass.Request request) {
      return blockingUnaryCall(
          getChannel(), getAvailableMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> put(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> coordinatorPut(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> get(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> delete(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> coordinatorDelete(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse> available(
        edu.neu.DatastoreService.DatastoreServiceOuterClass.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getAvailableMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUT = 0;
  private static final int METHODID_COORDINATOR_PUT = 1;
  private static final int METHODID_GET = 2;
  private static final int METHODID_DELETE = 3;
  private static final int METHODID_COORDINATOR_DELETE = 4;
  private static final int METHODID_AVAILABLE = 5;

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
        case METHODID_PUT:
          serviceImpl.put((edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>) responseObserver);
          break;
        case METHODID_COORDINATOR_PUT:
          serviceImpl.coordinatorPut((edu.neu.DatastoreService.DatastoreServiceOuterClass.PutRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((edu.neu.DatastoreService.DatastoreServiceOuterClass.GetRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>) responseObserver);
          break;
        case METHODID_COORDINATOR_DELETE:
          serviceImpl.coordinatorDelete((edu.neu.DatastoreService.DatastoreServiceOuterClass.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>) responseObserver);
          break;
        case METHODID_AVAILABLE:
          serviceImpl.available((edu.neu.DatastoreService.DatastoreServiceOuterClass.Request) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.DatastoreServiceOuterClass.APIResponse>) responseObserver);
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
              .addMethod(getPutMethod())
              .addMethod(getCoordinatorPutMethod())
              .addMethod(getGetMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getCoordinatorDeleteMethod())
              .addMethod(getAvailableMethod())
              .build();
        }
      }
    }
    return result;
  }
}
