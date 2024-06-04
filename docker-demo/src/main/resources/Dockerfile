FROM openjdk:17
MAINTAINER zhangenxu
VOLUME /temp
ADD docker-demo-1.0-SNAPSHOT.jar zhang_docker.jar
RUN bash -c 'touch /zhang_docker.jar'
ENTRYPOINT ["java","-jar","/zhang_docker.jar"]
EXPOSE 6001