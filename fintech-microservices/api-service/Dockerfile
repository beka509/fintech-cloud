FROM openjdk:17-alpine3.14
ENV TZ=Asia/Almaty
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
EXPOSE 23122
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","./app.jar"]