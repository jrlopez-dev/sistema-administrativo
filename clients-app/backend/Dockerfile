FROM maven:3.8.3-openjdk-17 as builder
WORKDIR /api
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /api

RUN apk add --no-cache curl
RUN adduser -D -u 10001 api

COPY --from=builder /api/target/api-sistema-administrativo-0.0.1-SNAPSHOT.jar ./api/
RUN chown -R api:api ./api

USER api

ENTRYPOINT ["java", "-jar", "./api/api-sistema-administrativo-0.0.1-SNAPSHOT.jar"]

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 CMD curl -f http://localhost:8080/actuator/health || exit 1
