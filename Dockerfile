FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8999
ADD target/springboot-crud-k8s.jar springboot-crud-k8s.jar
ENTRYPOINT ["java","-jar","/springboot-crud-k8s.jar"]