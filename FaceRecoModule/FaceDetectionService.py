import cv2
import numpy as np
import base64
from concurrent import futures
import grpc
import service_server_pb2
import service_server_pb2_grpc

class FaceDetectionServicer(service_server_pb2_grpc.FaceDetectionServiceServicer):
    def DetectFace(self, request, context):
        try:
            image_data = request.image_data
            image_array = np.frombuffer(base64.b64decode(image_data), dtype=np.uint8)
            if image_array.size == 0:
                context.set_code(grpc.StatusCode.INVALID_ARGUMENT)
                context.set_details('Received empty image data')
                return service_server_pb2.DetectionResponse(is_valid=False)

            image = cv2.imdecode(image_array, cv2.IMREAD_COLOR)

            gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

            face_cascade = cv2.CascadeClassifier('model/haarcascade_default.xml')
            if face_cascade.empty():
                context.set_code(grpc.StatusCode.INTERNAL)
                context.set_details('Haarcascade file not found or invalid')
                return service_server_pb2.DetectionResponse(is_valid=False)

            detections = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5, minSize=(20, 20))

            is_valid = len(detections) == 1

            return service_server_pb2.DetectionResponse(is_valid=is_valid)
        except Exception as e:
            context.set_code(grpc.StatusCode.INTERNAL)
            context.set_details(str(e))
            return service_server_pb2.DetectionResponse(is_valid=False)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    service_server_pb2_grpc.add_FaceDetectionServiceServicer_to_server(FaceDetectionServicer(), server)
    server.add_insecure_port('0.0.0.0:5678')  # Standard gRPC port
    print("gRPC server running on port 5678")
    server.start()
    server.wait_for_termination()

if __name__ == "__main__":
    serve()