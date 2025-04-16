# Étape de build (si nécessaire)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# Étape de production
FROM eclipse-temurin:17.0.10_7-jre
WORKDIR /app
COPY --from=build /build/target/agence-voiture-0.0.1-SNAPSHOT.jar app.jar

# Configuration de l'environnement Java
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Exposition du port (si nécessaire)
EXPOSE 8080

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
