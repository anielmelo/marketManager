# TUTORIAL DE CONFIGURAÇÃO DOS ARQUIVOS DOCKER

Cada API Spring Boot do projeto terá um arquivo Dockerfile com a seguinte configuração:

### DOCKERFILE PADRÃO

```docker
FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y \
    maven \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "target/authentication-0.0.1-SNAPSHOT.jar"]
# CMD ["tail", "-f", "/dev/null"]
```

### PASSOS PARA CONFIGURAÇÃO INICIAL

Ao buildar pela primeira vez, siga estes passos:
- Comente a linha `CMD ["java", "-jar", "target/authentication-0.0.1-SNAPSHOT.jar"]`;
- Tire o comentário da linha `CMD ["tail", "-f", "/dev/null"]`;
- Execute o comando `sudo docker-compose up --build` para iniciar os containers;
- Execute o comando `sudo docker exec -it <ID_DO_CONTAINER> bash` para acessar o container da API desejada;
- Execute o comando `mvn clean package` para buildar o projeto;
- E então, execute o comando `java -jar target/nome-do-executavel.jar` para iniciar o projeto (opcional);
- Por fim, retorne o Dockerfile ao padrão;
