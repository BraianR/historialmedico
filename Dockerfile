# Utilizar una imagen base oficial de Java (elige la versión adecuada para tu aplicación)
FROM openjdk:21-jdk

# Argumentos para construir con Maven o Gradle
ARG JAR_FILE=target/*.jar

# Copiar el jar de tu aplicación al contenedor
COPY ${JAR_FILE} app.jar

# Exponer el puerto en el que se ejecutará tu aplicación
EXPOSE 8089

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]