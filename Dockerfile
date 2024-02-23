FROM openjdk:17-oracle
EXPOSE 8080
VOLUME /tmp
COPY target/mvp-0.0.1-SNAPSHOT.jar mvp.jar
ENTRYPOINT ["java","-jar","/mvp.jar"]
