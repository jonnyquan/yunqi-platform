FROM java:8
VOLUME /tmp
ADD yunqi-user-service-1.0.0-SNAPSHOT.jar user.jar
RUN sh -c 'touch /user.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user.jar"]
