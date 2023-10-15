FROM openjdk:11-jre-slim

# Copy wait-for-it script into the image
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Use the script to wait for mysql-container to be available, then run your Java application
ENTRYPOINT ["/wait-for-it.sh", "mysql-container:3306", "--", "java", "-jar", "/app.jar"]
