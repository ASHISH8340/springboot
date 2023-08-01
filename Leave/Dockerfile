FROM openjdk:17
ADD ./target/Leave-0.0.1-SNAPSHOT.jar /usr/src/Leave-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "Leave-0.0.1-SNAPSHOT.jar"]