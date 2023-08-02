FROM openjdk:11
ADD ./target/order-Service-0.0.1-SNAPSHOT.jar /usr/src/order-Service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "order-Service-0.0.1-SNAPSHOT.jar"]