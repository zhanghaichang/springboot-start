# springboot-docker 使用Docker部署 spring-boot maven应用

部署过程分为以下几个步骤:

* 1.创建一个简单的spring-boot应用
* 2.打包运行应用
* 3.容器化应用
* 4.在pom文件中添加docker支持
* 5.创建docker镜像
* 6.运行docker容器
* 7.查看正在运行的容器
* 8.启动/关闭/重启/删除docker容器

## 1. 创建一个简单的spring-boot应用



## 2. 打包运行应用
pom.xml中添加依赖:
```maven
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
添加一个控制器:
```java
@Controller
public class HelloWorld{
    @GetMapping("/hello")
    public void helloworld(HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello Spring-boot");
    }
}
```

运行项目,访问: http://localhost:8080/hello,如果出现: Hello Spring-boot 第二步完成.

## 3. 容器化应用
在项目目录创建Dockerfile文件: src/main/docker/Dockerfile(没有后缀),内容如下:

```
FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD demo-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
```
其中 demo-0.0.1-SNAPSHOT.jar 是项目打包后 /target/ 里面的文件名

## 4. 在pom文件中添加docker支持
```maven
<properties>
   <docker.image.prefix>ramer</docker.image.prefix>
</properties>
<build>
    <plugins>
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>docker-maven-plugin</artifactId>
            <version>0.4.11</version>
            <configuration>
                <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
                <dockerDirectory>src/main/docker</dockerDirectory>
                <resources>
                    <resource>
                        <targetPath>/</targetPath>
                        <directory>${project.build.directory}</directory>
                        <include>${project.build.finalName}.jar</include>
                    </resource>
                </resources>
            </configuration>
        </plugin>
    </plugins>
</build>
```
## 5. 创建docker镜像
cmd进入到当前目录:

cd Z:/Desktop/springboot-demo

* 打包,创建镜像:

mvn package -Dmaven.test.skip=true docker:build

注意: 请确保maven已添加到path中;并且已安装docker,如果没有安装,请访问: https://www.docker.com/community-edition#/download 下载合适的版本.

## 6. 运行docker容器最好添加 --name参数

docker run --name=springboot-docker-demo -p 8080:8080 -t ramer/demo

在浏览器访问: http://localhost:8080/hello

## 7. 查看正在运行的容器
cmd: docker ps

## 8. 启动/关闭/重启/删除docker容器

cmd: docker start/stop/restart/rm CONTAINER_ID/NAME 
其中: 
CONTAINER_ID: 是容器id,执行 docker ps 可查看 
NAME: 是容器的名称,也就是docker run –name后面的名字
