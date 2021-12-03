package edu.neu.DatastoreService.Learner;

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
    comments = "Source: Learner.proto")
public final class LearnerGrpc {

  private LearnerGrpc() {}

  public static final String SERVICE_NAME = "Learner";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest,
      edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse> getUpdateDatastoreMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateDatastore",
      requestType = edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest.class,
      responseType = edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest,
      edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse> getUpdateDatastoreMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest, edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse> getUpdateDatastoreMethod;
    if ((getUpdateDatastoreMethod = LearnerGrpc.getUpdateDatastoreMethod) == null) {
      synchronized (LearnerGrpc.class) {
        if ((getUpdateDatastoreMethod = LearnerGrpc.getUpdateDatastoreMethod) == null) {
          LearnerGrpc.getUpdateDatastoreMethod = getUpdateDatastoreMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest, edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Learner", "updateDatastore"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new LearnerMethodDescriptorSupplier("updateDatastore"))
                  .build();
          }
        }
     }
     return getUpdateDatastoreMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LearnerStub newStub(io.grpc.Channel channel) {
    return new LearnerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LearnerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LearnerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LearnerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LearnerFutureStub(channel);
  }

  /**
   */
  public static abstract class LearnerImplBase implements io.grpc.BindableService {

    /**
     */
    public void updateDatastore(edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateDatastoreMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUpdateDatastoreMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest,
                edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse>(
                  this, METHODID_UPDATE_DATASTORE)))
          .build();
    }
  }

  /**
   */
  public static final class LearnerStub extends io.grpc.stub.AbstractStub<LearnerStub> {
    private LearnerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LearnerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LearnerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LearnerStub(channel, callOptions);
    }

    /**
     */
    public void updateDatastore(edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateDatastoreMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LearnerBlockingStub extends io.grpc.stub.AbstractStub<LearnerBlockingStub> {
    private LearnerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LearnerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LearnerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LearnerBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse updateDatastore(edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateDatastoreMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LearnerFutureStub extends io.grpc.stub.AbstractStub<LearnerFutureStub> {
    private LearnerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LearnerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LearnerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LearnerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse> updateDatastore(
        edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateDatastoreMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPDATE_DATASTORE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LearnerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LearnerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPDATE_DATASTORE:
          serviceImpl.updateDatastore((edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.Learner.LearnerOuterClass.UpdateResponse>) responseObserver);
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

  private static abstract class LearnerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LearnerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.neu.DatastoreService.Learner.LearnerOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Learner");
    }
  }

  private static final class LearnerFileDescriptorSupplier
      extends LearnerBaseDescriptorSupplier {
    LearnerFileDescriptorSupplier() {}
  }

  private static final class LearnerMethodDescriptorSupplier
      extends LearnerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LearnerMethodDescriptorSupplier(String methodName) {
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
      synchronized (LearnerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LearnerFileDescriptorSupplier())
              .addMethod(getUpdateDatastoreMethod())
              .build();
        }
      }
    }
    return result;
  }
}
