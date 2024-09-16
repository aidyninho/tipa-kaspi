FROM openjdk:21-oracle

LABEL authors="aidyninho"

COPY build/libs/tipa-kaspi-0.0.1-SNAPSHOT.jar tipa-kaspi.jar

ENTRYPOINT ["java", "-jar", "tipa-kaspi.jar"]