FROM openjdk:8-jre-alpine
FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS="-Dspring.profiles.active=stg" \
    DOCKER_HOST="http://192.168.160.189:2375"

# Add a jhipster user to run our application so that it doesn't need to run as root
# RUN adduser -D -s /bin/sh jhipster

WORKDIR /home/jhipster
 #构建应用
RUN mvn clean package
COPY ./docker-entrypoint.sh
ADD target/entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown jhipster:jhipster entrypoint.sh
USER jhipster

ADD *.war app.war

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

