import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Choose Input File:");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        String inputFileName = "input_" + choice + ".txt";
        String outputFilename = "data/output_" + choice + ".txt";

        InputHandler inputHandler = new InputHandler(inputFileName);
        inputHandler.readInputFile();

        final Bank bank = new Bank(inputHandler.getInitialReserve(),
                inputHandler.getTransactionNumber(),
                inputHandler.getQueryNumber(),
                inputHandler.getCommands());
        bank.runCommands();

        bank.printOutputsOnFile(outputFilename);
    }
}