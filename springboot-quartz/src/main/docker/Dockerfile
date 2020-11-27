FROM 172.18.161.165:8888/base/oracle-jre-1.8:v2.0.0
MAINTAINER zhanghaichang <zhanghc@topcheer.com>
ARG JAR_FILE
ADD ${JAR_FILE}.jar /home/x/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/x/app.jar"]