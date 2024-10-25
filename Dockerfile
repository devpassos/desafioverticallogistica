# imagem oficial do OpenJDK
FROM openjdk:17-jdk-slim

# Definindo  diretório de trabalho
WORKDIR /app

# Copiando o arquivo JAR gerado para dentro do container
COPY target/desafio-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta em que a aplicação será executada
EXPOSE 8080

# Define o comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]