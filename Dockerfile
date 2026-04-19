FROM openjdk:11-ea-19-jre-slim
EXPOSE 8080
ADD target/banco2026v.jar banco2026v.jar
ENTRYPOINT ["java","-jar","/banco2026v.jar"]