# FROM <image>[:<tag>|@<digest]
FROM maven:3.8.6-eclipse-temurin-11-alpine as builder

LABEL Fatih Celik <celikfatih@protonmail.com>

WORKDIR /opt/app
COPY . .
RUN mvn clean package

FROM eclipse-temurin:11-jdk-alpine

RUN addgroup casegroup; adduser --ingroup casegroup --disabled-password fati
USER fati

WORKDIR /home/app
COPY --from=builder /opt/app/target/*.jar /home/app/n11-case.jar

ENTRYPOINT ["java", "-jar", "/home/app/n11-case.jar"]
