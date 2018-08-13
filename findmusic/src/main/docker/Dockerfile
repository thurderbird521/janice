FROM openjdk:8-jre-alpine
FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS="-Dspring.profiles.active=stg" \
    DOCKER_HOST="http://192.168.160.189:2375"

ADD pom.xml /tmp/build/
 
ADD src /tmp/build/src
        #构建应用
RUN cd /tmp/build && mvn clean package -Dmaven.skip.test=true \
        #拷贝编译结果到指定目录
        && mv target/*.war /app.war \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build
VOLUME /tmp

COPY ./docker-entrypoint.sh
ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

