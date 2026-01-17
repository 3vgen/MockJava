FROM amazoncorretto:17.0.3-alpine as corretto-jdk
ADD /target/newMock-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar", "--server.address=0.0.0.0"]
