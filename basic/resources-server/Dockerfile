FROM openjdk:8-jdk-slim

RUN mkdir -p /app/java
WORKDIR /app/java

COPY . .

#build
RUN ./gradlew build -x test

CMD ["java", "-jar", "-server", "-Duser.timezone=Asia/Seoul", "-Dfile.encoding=utf-8", "-Dspring.profiles.active=docker", "build/libs/basic-res-server-1.0.0.jar"]