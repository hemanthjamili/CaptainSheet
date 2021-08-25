FROM java:8

EXPOSE 8080

ADD target/captainfresh-assignment.jar captainfresh-assignment.jar

ENTRYPOINT ["java", "-jar", "captainfresh-assignment.jar"]