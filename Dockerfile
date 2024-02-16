FROM maven:3.9-amazoncorretto-17 AS builder
WORKDIR /app
COPY ./pom.xml ./
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean package

FROM openjdk:17-jdk-buster

COPY --from=builder /app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=builder /app/target/quarkus-app/*.jar /deployments/
COPY --from=builder  /app/target/quarkus-app/app/ /deployments/app/
COPY --from=builder /app/target/quarkus-app/quarkus/ /deployments/quarkus/
ENTRYPOINT ["java","-jar","/deployments/quarkus-run.jar"]
