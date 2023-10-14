FROM ubuntu:latest AS build

RUN apt-get update
RUN sudo apt-get install openjdk-21-jre-headless

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-slim

EXPOSE 8080

COPY --from=build /target/todolist-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]


