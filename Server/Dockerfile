FROM bellsoft/liberica-openjdk-alpine-musl:11 AS server0-build
COPY target/DatastoreServer.jar /usr/server.jar
WORKDIR /usr

FROM bellsoft/liberica-openjdk-alpine-musl:11 AS server1-build
COPY target/DatastoreServer.jar /usr/server.jar
WORKDIR /usr

FROM bellsoft/liberica-openjdk-alpine-musl:11 AS server2-build
COPY target/DatastoreServer.jar /usr/server.jar
WORKDIR /usr