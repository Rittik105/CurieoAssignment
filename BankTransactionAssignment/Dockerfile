FROM openjdk:17
WORKDIR /app
COPY src /app/src
COPY *.txt /app/
RUN javac -d /app/classes /app/src/*.java
ENTRYPOINT ["java", "-classpath", "/app/classes", "Main"]

