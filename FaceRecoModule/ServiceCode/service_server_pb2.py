# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# NO CHECKED-IN PROTOBUF GENCODE
# source: service-server.proto
# Protobuf Python Version: 5.28.1
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import runtime_version as _runtime_version
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
_runtime_version.ValidateProtobufRuntimeVersion(
    _runtime_version.Domain.PUBLIC,
    5,
    28,
    1,
    '',
    'service-server.proto'
)
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x14service-server.proto\x12\x0cphoto_server\"\"\n\x0cImageRequest\x12\x12\n\nimage_data\x18\x01 \x01(\x0c\"%\n\x11\x44\x65tectionResponse\x12\x10\n\x08is_valid\x18\x01 \x01(\x08\x32\x61\n\x14\x46\x61\x63\x65\x44\x65tectionService\x12I\n\nDetectFace\x12\x1a.photo_server.ImageRequest\x1a\x1f.photo_server.DetectionResponseb\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'service_server_pb2', _globals)
if not _descriptor._USE_C_DESCRIPTORS:
  DESCRIPTOR._loaded_options = None
  _globals['_IMAGEREQUEST']._serialized_start=38
  _globals['_IMAGEREQUEST']._serialized_end=72
  _globals['_DETECTIONRESPONSE']._serialized_start=74
  _globals['_DETECTIONRESPONSE']._serialized_end=111
  _globals['_FACEDETECTIONSERVICE']._serialized_start=113
  _globals['_FACEDETECTIONSERVICE']._serialized_end=210
# @@protoc_insertion_point(module_scope)
