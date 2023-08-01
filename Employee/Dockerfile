FROM openjdk:17
ADD ./target/Employee-0.0.1-SNAPSHOT.jar /usr/src/Employee-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "Employee-0.0.1-SNAPSHOT.jar"]