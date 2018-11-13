FROM java:8-alpine

RUN mkdir -p /app /app/resources

WORKDIR /app

COPY target/*.jar /app/
COPY resources/public resources/public

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app/edn-json-converter-api.jar"]
