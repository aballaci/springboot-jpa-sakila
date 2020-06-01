FROM maven:3.6.3-jdk-11 as MAVEN_BUILD

MAINTAINER Armand Ballaci

COPY pom.xml .

COPY settings.xml "/root/.m2/settings.xml"

COPY src src

RUN mvn dependency:go-offline -B

RUN mvn clean package -DskipTests

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Production Stage for Spring boot application image
# Also, there is a clean separation between dependencies and application resources in a Spring Boot fat jar file,
# and we can use that fact to improve performance. The key is to create layers in the container filesystem.
# The layers are cached both at build time and at runtime (in most runtimes) so we want the most frequently changing resources,
# usually the class and static resources in the application itself, to be layered after the more slowly changing resources.
# Thus we will use a slightly different implementation of the Dockerfile: https://spring.io/guides/gs/spring-boot-docker/
FROM openjdk:11-jdk-slim as production
ARG DEPENDENCY=target/dependency
COPY --from=MAVEN_BUILD ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=MAVEN_BUILD ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=MAVEN_BUILD ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8080
ENTRYPOINT ["java","-cp","app:app/lib/*","de.ballaci.jpa.SakilaApplication"]