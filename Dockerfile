FROM openjdk:8
VOLUME /tmp
ADD target/withdrawal-0.0.1-SNAPSHOT.jar withdrawal-0.0.1-SNAPSHOT.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","withdrawal-0.0.1-SNAPSHOT.jar"]