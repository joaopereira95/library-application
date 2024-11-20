FROM maven:3-openjdk-21 as maven
LABEL maintainer 'Joao Eduardo Pereira'

COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests=true

FROM openjdk:21
LABEL maintainer 'Joao Eduardo Pereira'
WORKDIR /target
COPY --from=maven target/*.jar ./

EXPOSE 8080

COPY scripts/wait-for-it.sh ./

RUN chmod +x wait-for-it.sh

ENTRYPOINT ["bash", "./wait-for-it.sh", "library-db:5432", "-t", "60", "--"]

CMD java -jar library-api-0.0.1-SNAPSHOT.jar