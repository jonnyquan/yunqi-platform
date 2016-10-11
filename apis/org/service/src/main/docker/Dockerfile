FROM java:8
VOLUME /tmp
ADD yunqi-org-service-1.0.1-SNAPSHOT.jar org.jar
RUN sh -c 'touch /org.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/org.jar"]
