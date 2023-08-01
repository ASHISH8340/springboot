FROM openjdk:17
ADD ./target/config-0.0.1-SNAPSHOT.jar /usr/src/config-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "config-0.0.1-SNAPSHOT.jar"]