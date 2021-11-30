package edu.neu.DatastoreService.Acceptor;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Acceptor.proto")
public final class AcceptorGrpc {

  private AcceptorGrpc() {}

  public static final String SERVICE_NAME = "Acceptor";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<AcceptorOuterClass.PrepareRequest,
      AcceptorOuterClass.PrepareResponse> getGetPromiseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPromise",
      requestType = AcceptorOuterClass.PrepareRequest.class,
      responseType = AcceptorOuterClass.PrepareResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<AcceptorOuterClass.PrepareRequest,
      AcceptorOuterClass.PrepareResponse> getGetPromiseMethod() {
    io.grpc.MethodDescriptor<AcceptorOuterClass.PrepareRequest, AcceptorOuterClass.PrepareResponse> getGetPromiseMethod;
    if ((getGetPromiseMethod = AcceptorGrpc.getGetPromiseMethod) == null) {
      synchronized (AcceptorGrpc.class) {
        if ((getGetPromiseMethod = AcceptorGrpc.getGetPromiseMethod) == null) {
          AcceptorGrpc.getGetPromiseMethod = getGetPromiseMethod = 
              io.grpc.MethodDescriptor.<AcceptorOuterClass.PrepareRequest, AcceptorOuterClass.PrepareResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Acceptor", "getPromise"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AcceptorOuterClass.PrepareRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AcceptorOuterClass.PrepareResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AcceptorMethodDescriptorSupplier("getPromise"))
                  .build();
          }
        }
     }
     return getGetPromiseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<AcceptorOuterClass.ProposeRequest,
      AcceptorOuterClass.ProposeResponse> getGetAcceptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAccept",
      requestType = AcceptorOuterClass.ProposeRequest.class,
      responseType = AcceptorOuterClass.ProposeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<AcceptorOuterClass.ProposeRequest,
      AcceptorOuterClass.ProposeResponse> getGetAcceptMethod() {
    io.grpc.MethodDescriptor<AcceptorOuterClass.ProposeRequest, AcceptorOuterClass.ProposeResponse> getGetAcceptMethod;
    if ((getGetAcceptMethod = AcceptorGrpc.getGetAcceptMethod) == null) {
      synchronized (AcceptorGrpc.class) {
        if ((getGetAcceptMethod = AcceptorGrpc.getGetAcceptMethod) == null) {
          AcceptorGrpc.getGetAcceptMethod = getGetAcceptMethod = 
              io.grpc.MethodDescriptor.<AcceptorOuterClass.ProposeRequest, AcceptorOuterClass.ProposeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Acceptor", "getAccept"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AcceptorOuterClass.ProposeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AcceptorOuterClass.ProposeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AcceptorMethodDescriptorSupplier("getAccept"))
                  .build();
          }
        }
     }
     return getGetAcceptMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AcceptorStub newStub(io.grpc.Channel channel) {
    return new AcceptorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AcceptorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AcceptorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AcceptorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AcceptorFutureStub(channel);
  }

  /**
   */
  public static abstract class AcceptorImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<AcceptorOuterClass.PrepareRequest> getPromise(
        io.grpc.stub.StreamObserver<AcceptorOuterClass.PrepareResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetPromiseMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<AcceptorOuterClass.ProposeRequest> getAccept(
        io.grpc.stub.StreamObserver<AcceptorOuterClass.ProposeResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetAcceptMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetPromiseMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                AcceptorOuterClass.PrepareRequest,
                AcceptorOuterClass.PrepareResponse>(
                  this, METHODID_GET_PROMISE)))
          .addMethod(
            getGetAcceptMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                AcceptorOuterClass.ProposeRequest,
                AcceptorOuterClass.ProposeResponse>(
                  this, METHODID_GET_ACCEPT)))
          .build();
    }
  }

  /**
   */
  public static final class AcceptorStub extends io.grpc.stub.AbstractStub<AcceptorStub> {
    private AcceptorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AcceptorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AcceptorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AcceptorStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<AcceptorOuterClass.PrepareRequest> getPromise(
        io.grpc.stub.StreamObserver<AcceptorOuterClass.PrepareResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetPromiseMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<AcceptorOuterClass.ProposeRequest> getAccept(
        io.grpc.stub.StreamObserver<AcceptorOuterClass.ProposeResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetAcceptMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class AcceptorBlockingStub extends io.grpc.stub.AbstractStub<AcceptorBlockingStub> {
    private AcceptorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AcceptorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AcceptorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AcceptorBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class AcceptorFutureStub extends io.grpc.stub.AbstractStub<AcceptorFutureStub> {
    private AcceptorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AcceptorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AcceptorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AcceptorFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_PROMISE = 0;
  private static final int METHODID_GET_ACCEPT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AcceptorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AcceptorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PROMISE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getPromise(
              (io.grpc.stub.StreamObserver<AcceptorOuterClass.PrepareResponse>) responseObserver);
        case METHODID_GET_ACCEPT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getAccept(
              (io.grpc.stub.StreamObserver<AcceptorOuterClass.ProposeResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AcceptorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AcceptorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AcceptorOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Acceptor");
    }
  }

  private static final class AcceptorFileDescriptorSupplier
      extends AcceptorBaseDescriptorSupplier {
    AcceptorFileDescriptorSupplier() {}
  }

  private static final class AcceptorMethodDescriptorSupplier
      extends AcceptorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AcceptorMethodDescriptorSupplier(String methodName) {
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
      synchronized (AcceptorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AcceptorFileDescriptorSupplier())
              .addMethod(getGetPromiseMethod())
              .addMethod(getGetAcceptMethod())
              .build();
        }
      }
    }
    return result;
  }
}