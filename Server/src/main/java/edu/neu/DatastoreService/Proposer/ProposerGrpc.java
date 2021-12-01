package edu.neu.DatastoreService.Proposer;

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
    comments = "Source: Proposer.proto")
public final class ProposerGrpc {

  private ProposerGrpc() {}

  public static final String SERVICE_NAME = "Proposer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest,
      edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse> getGetConsensusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getConsensus",
      requestType = edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest.class,
      responseType = edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest,
      edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse> getGetConsensusMethod() {
    io.grpc.MethodDescriptor<edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest, edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse> getGetConsensusMethod;
    if ((getGetConsensusMethod = ProposerGrpc.getGetConsensusMethod) == null) {
      synchronized (ProposerGrpc.class) {
        if ((getGetConsensusMethod = ProposerGrpc.getGetConsensusMethod) == null) {
          ProposerGrpc.getGetConsensusMethod = getGetConsensusMethod = 
              io.grpc.MethodDescriptor.<edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest, edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Proposer", "getConsensus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ProposerMethodDescriptorSupplier("getConsensus"))
                  .build();
          }
        }
     }
     return getGetConsensusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProposerStub newStub(io.grpc.Channel channel) {
    return new ProposerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProposerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ProposerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProposerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ProposerFutureStub(channel);
  }

  /**
   */
  public static abstract class ProposerImplBase implements io.grpc.BindableService {

    /**
     */
    public void getConsensus(edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetConsensusMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetConsensusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest,
                edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse>(
                  this, METHODID_GET_CONSENSUS)))
          .build();
    }
  }

  /**
   */
  public static final class ProposerStub extends io.grpc.stub.AbstractStub<ProposerStub> {
    private ProposerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProposerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProposerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProposerStub(channel, callOptions);
    }

    /**
     */
    public void getConsensus(edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest request,
        io.grpc.stub.StreamObserver<edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetConsensusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProposerBlockingStub extends io.grpc.stub.AbstractStub<ProposerBlockingStub> {
    private ProposerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProposerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProposerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProposerBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse getConsensus(edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetConsensusMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProposerFutureStub extends io.grpc.stub.AbstractStub<ProposerFutureStub> {
    private ProposerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProposerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProposerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProposerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse> getConsensus(
        edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetConsensusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CONSENSUS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProposerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProposerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CONSENSUS:
          serviceImpl.getConsensus((edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusRequest) request,
              (io.grpc.stub.StreamObserver<edu.neu.DatastoreService.Proposer.ProposerOuterClass.ConsensusResponse>) responseObserver);
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

  private static abstract class ProposerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProposerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.neu.DatastoreService.Proposer.ProposerOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Proposer");
    }
  }

  private static final class ProposerFileDescriptorSupplier
      extends ProposerBaseDescriptorSupplier {
    ProposerFileDescriptorSupplier() {}
  }

  private static final class ProposerMethodDescriptorSupplier
      extends ProposerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProposerMethodDescriptorSupplier(String methodName) {
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
      synchronized (ProposerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProposerFileDescriptorSupplier())
              .addMethod(getGetConsensusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
