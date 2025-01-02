FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y \
    maven \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "target/authentication-0.0.1-SNAPSHOT.jar"]
#CMD ["tail", "-f", "/dev/null"]
