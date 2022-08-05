FROM ghcr.io/graalvm/jdk:java17
RUN groupadd spring && useradd -g spring spring
USER spring
WORKDIR /app

ADD build/libs/gateway-1.0.0.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /app/app.jar" ]