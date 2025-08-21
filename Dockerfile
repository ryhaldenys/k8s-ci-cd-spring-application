FROM alpine/java:21-jre
LABEL authors="denys.ryhal"
WORKDIR /app
ADD ./k8s-ci-cd-config/build/libs/k8s-ci-cd-spring-application.jar k8s-ci-cd-spring-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "k8s-ci-cd-spring-application.jar"]