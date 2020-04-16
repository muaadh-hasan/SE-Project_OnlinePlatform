FROM openjdk:8
EXPOSE 8090
ADD target/se-web-online-store.jar se-web-online-store.jar
ENTRYPOINT ["java","-jar","se-web-online-store.jar"]