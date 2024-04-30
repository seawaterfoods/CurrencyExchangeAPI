FROM maven:3.8.6-openjdk-11 AS builder

WORKDIR /tmp

COPY ./pom_local.xml ./pom.xml

RUN mvn dependency:go-offline

COPY ./src ./src

RUN mvn package -Plocal

COPY ./manifests ./manifests



FROM openjdk:11.0

ENV TZ=Asia/Hong_Kong

RUN addgroup --system spring && adduser --system --group spring
USER spring:spring

WORKDIR /tmp

ARG JAR_FILE=/tmp/target/*.jar
ARG CREDENTIAL_FILE=/tmp/manifests/deploy/local/credential.yaml
COPY --from=builder ${JAR_FILE} hktv-ty-mwms.jar
COPY --from=builder ${CREDENTIAL_FILE} credential.yaml

ENTRYPOINT exec java $JAVA_OPTS -jar ./hktv-ty-mwms.jar