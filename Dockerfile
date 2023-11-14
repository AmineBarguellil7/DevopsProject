FROM openjdk:11

ADD target/*.jar ski.jar

ENTRYPOINT ["java", "-jar", "ski.jar"]
