FROM openjdk:8
MAINTAINER "hcycle.dev2k20@gmail.com"
ADD target/api-jenkins-docker.jar api-jenkins-docker.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "api-jenkins-docker.jar"]