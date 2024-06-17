FROM openjdk:22
WORKDIR /app
COPY target/BankTransactionAssignment.jar
/app/BankTransactionAssignment.jar
ENTRYPOINT ["java", "-jar", "BankTransactionAssignment.jar"]