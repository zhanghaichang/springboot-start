FROM frolvlad/alpine-oraclejdk8:slim
VOLUME ["~/data/logs/","/data/logs/"]
ADD springboot-helloworld-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]