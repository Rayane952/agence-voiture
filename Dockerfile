FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/agence-voiture-0.0.1-SNAPSHOT.jar app.jar

# Créer le répertoire pour les images statiques
RUN mkdir -p /app/static/img

ENTRYPOINT ["java", "-jar", "app.jar"]
