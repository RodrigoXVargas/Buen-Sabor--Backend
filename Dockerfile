FROM openjdk:17
EXPOSE 7455
ADD ./target/Buen-Sabor-0.0.1-SNAPSHOT.jar ejecutableBS.jar
ENTRYPOINT ["java", "-jar", "/ejecutableBS.jar"]