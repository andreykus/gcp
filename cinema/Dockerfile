#
# Copyright © 2021 The Akvelon Authors
#
#
FROM openjdk:8u171-jdk-alpine3.7
MAINTAINER Andrew Kustov "andreykus@mail.ru"

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/cinema/cinema-1.0-SNAPSHOT.jar"]
# Add Maven dependencies (not shaded into the artifact; Docker-cached)
# ADD target/lib           /usr/share/cinema/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/cinema/cinema-1.0-SNAPSHOT.jar
EXPOSE 8080
