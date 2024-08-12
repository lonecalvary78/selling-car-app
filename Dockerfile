FROM maven:3.9.6-eclipse-temurin-21 AS builder
COPY ./src ./src
COPY pom.xml .
RUN mvn clean install -U -DskipTests

FROM eclipse-temurin:21-jre
ENV QUARKUS_PROFILE="dev"
ENV DB_URL=""
ENV DB_USER=""
ENV DB_PWD=""
COPY --from=builder target/quarkus-app/lib/ /car-selling-app/lib/
COPY --from=builder target/quarkus-app/*.jar /car-selling-app/
COPY --from=builder target/quarkus-app/app/ /car-selling-app/app/
COPY --from=builder target/quarkus-app/quarkus/ /car-selling-app/quarkus/

EXPOSE 8080
ENTRYPOINT ["java","-jar","/car-selling-app/quarkus-run.jar","Dquarkus.http.port=8080","-Dquarkus.profile=$QUARKUS_PROFILE","-Dquarkus.datasource.jdbc.url=$DB_URL","-Dquarkus.datasource.username=$DB_USER","-Dquarkus.datasource.password=$DB_PWD"]
