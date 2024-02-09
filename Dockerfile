FROM eclipse-temurin:17
LABEL authors="plipka"
VOLUME /tmp/e2
WORKDIR /app
ARG JAR_FILE
COPY ${JAR_FILE} ./app.jar
CMD ["java", "-DMYSQL_HOST=mysql-e2", "-DMYSQL_PORT=3306", "-jar", "app.jar"]