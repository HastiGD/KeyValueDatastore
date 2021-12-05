FROM bellsoft/liberica-openjdk-alpine-musl:11 AS server0-build
COPY target/DatastoreServer.jar /usr/server.jar
WORKDIR /usr
CMD ["java", "-jar", "server.jar", "localhost", "11000", "localhost", "11001", "localhost", "11002"]

FROM bellsoft/liberica-openjdk-alpine-musl:11 AS server1-build
COPY target/DatastoreServer.jar /usr/server.jar
WORKDIR /usr
CMD ["java", "-jar", "server.jar", "localhost", "11001", "localhost", "11002", "localhost", "11000"]

FROM bellsoft/liberica-openjdk-alpine-musl:11 AS server2-build
COPY target/DatastoreServer.jar /usr/server.jar
WORKDIR /usr
CMD ["java", "-jar", "server.jar", "localhost", "11002", "localhost", "11000", "localhost", "11001"]