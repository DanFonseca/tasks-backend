FROM tomcat:9.0.35-jdk14-openjdk-oracle

ARG WAR_FILE
ARG CONTEXT

COPY ${WAR_FILE} /usr/local/tomcat/webapps/${CONTEXT}.war
