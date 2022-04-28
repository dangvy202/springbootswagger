FROM eclipse-temurin:11

VOLUME /tmp

# Add Spring Boot app.jar to Container
RUN mkdir -p /var/spring-boot-app
WORKDIR /var/spring-boot-app
ADD "target/*.jar" app.jar
RUN mkdir -p config
COPY src/main/resources/* config
RUN ls /var/spring-boot-app/config/

ENV JAVA_OPTS=""

EXPOSE 9999

# Fire up our Spring Boot app by default
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /var/spring-boot-app/app.jar", "--spring.config.location=/var/spring-boot-app/config" ]
