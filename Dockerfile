FROM openjdk:17
EXPOSE 8086
ADD target/sample-inventory.jar sample-inventory.jar
ENTRYPOINT [ "java", "-jar", "/sample-inventory.jar"]