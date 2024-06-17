FROM openjdk:17
WORKDIR /app
COPY src/*java .
COPY *.txt .
RUN javac *.java
CMD ["java", "Main"]
