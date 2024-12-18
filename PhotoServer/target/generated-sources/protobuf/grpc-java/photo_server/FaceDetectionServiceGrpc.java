package photo_server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Define the gRPC service for face detection
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.1)",
    comments = "Source: service-server.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FaceDetectionServiceGrpc {

  private FaceDetectionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "photo_server.FaceDetectionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<photo_server.ServiceServer.ImageRequest,
      photo_server.ServiceServer.DetectionResponse> getDetectFaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DetectFace",
      requestType = photo_server.ServiceServer.ImageRequest.class,
      responseType = photo_server.ServiceServer.DetectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<photo_server.ServiceServer.ImageRequest,
      photo_server.ServiceServer.DetectionResponse> getDetectFaceMethod() {
    io.grpc.MethodDescriptor<photo_server.ServiceServer.ImageRequest, photo_server.ServiceServer.DetectionResponse> getDetectFaceMethod;
    if ((getDetectFaceMethod = FaceDetectionServiceGrpc.getDetectFaceMethod) == null) {
      synchronized (FaceDetectionServiceGrpc.class) {
        if ((getDetectFaceMethod = FaceDetectionServiceGrpc.getDetectFaceMethod) == null) {
          FaceDetectionServiceGrpc.getDetectFaceMethod = getDetectFaceMethod =
              io.grpc.MethodDescriptor.<photo_server.ServiceServer.ImageRequest, photo_server.ServiceServer.DetectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DetectFace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  photo_server.ServiceServer.ImageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  photo_server.ServiceServer.DetectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FaceDetectionServiceMethodDescriptorSupplier("DetectFace"))
              .build();
        }
      }
    }
    return getDetectFaceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FaceDetectionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FaceDetectionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FaceDetectionServiceStub>() {
        @java.lang.Override
        public FaceDetectionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FaceDetectionServiceStub(channel, callOptions);
        }
      };
    return FaceDetectionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FaceDetectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FaceDetectionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FaceDetectionServiceBlockingStub>() {
        @java.lang.Override
        public FaceDetectionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FaceDetectionServiceBlockingStub(channel, callOptions);
        }
      };
    return FaceDetectionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FaceDetectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FaceDetectionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FaceDetectionServiceFutureStub>() {
        @java.lang.Override
        public FaceDetectionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FaceDetectionServiceFutureStub(channel, callOptions);
        }
      };
    return FaceDetectionServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Define the gRPC service for face detection
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void detectFace(photo_server.ServiceServer.ImageRequest request,
        io.grpc.stub.StreamObserver<photo_server.ServiceServer.DetectionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDetectFaceMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FaceDetectionService.
   * <pre>
   * Define the gRPC service for face detection
   * </pre>
   */
  public static abstract class FaceDetectionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FaceDetectionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FaceDetectionService.
   * <pre>
   * Define the gRPC service for face detection
   * </pre>
   */
  public static final class FaceDetectionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FaceDetectionServiceStub> {
    private FaceDetectionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FaceDetectionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FaceDetectionServiceStub(channel, callOptions);
    }

    /**
     */
    public void detectFace(photo_server.ServiceServer.ImageRequest request,
        io.grpc.stub.StreamObserver<photo_server.ServiceServer.DetectionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDetectFaceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FaceDetectionService.
   * <pre>
   * Define the gRPC service for face detection
   * </pre>
   */
  public static final class FaceDetectionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FaceDetectionServiceBlockingStub> {
    private FaceDetectionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FaceDetectionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FaceDetectionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public photo_server.ServiceServer.DetectionResponse detectFace(photo_server.ServiceServer.ImageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDetectFaceMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FaceDetectionService.
   * <pre>
   * Define the gRPC service for face detection
   * </pre>
   */
  public static final class FaceDetectionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FaceDetectionServiceFutureStub> {
    private FaceDetectionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FaceDetectionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FaceDetectionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<photo_server.ServiceServer.DetectionResponse> detectFace(
        photo_server.ServiceServer.ImageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDetectFaceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DETECT_FACE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DETECT_FACE:
          serviceImpl.detectFace((photo_server.ServiceServer.ImageRequest) request,
              (io.grpc.stub.StreamObserver<photo_server.ServiceServer.DetectionResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getDetectFaceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              photo_server.ServiceServer.ImageRequest,
              photo_server.ServiceServer.DetectionResponse>(
                service, METHODID_DETECT_FACE)))
        .build();
  }

  private static abstract class FaceDetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FaceDetectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return photo_server.ServiceServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FaceDetectionService");
    }
  }

  private static final class FaceDetectionServiceFileDescriptorSupplier
      extends FaceDetectionServiceBaseDescriptorSupplier {
    FaceDetectionServiceFileDescriptorSupplier() {}
  }

  private static final class FaceDetectionServiceMethodDescriptorSupplier
      extends FaceDetectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FaceDetectionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (FaceDetectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FaceDetectionServiceFileDescriptorSupplier())
              .addMethod(getDetectFaceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
