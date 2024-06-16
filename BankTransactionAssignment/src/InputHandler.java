import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    String fileName;
    File inputFile;
    List<String> lines;

    public InputHandler(String fileName){
        this.fileName = fileName;
        inputFile = new File(fileName);
        lines = new ArrayList<>();
    }

    public void readInputFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public BigDecimal getInitialReserve(){
        String[] firstLine = lines.get(0).split(" ");
        return new BigDecimal(firstLine[0]);
    }

    public Integer getTransactionNumber(){
        String[] firstLine = lines.get(0).split(" ");
        return Integer.getInteger(firstLine[1]);
    }

    public Integer getQueryNumber(){
        String[] firstLine = lines.get(0).split(" ");
        return Integer.getInteger(firstLine[2]);
    }

    public List<Command> getCommands(){
        List<Command> commands = new ArrayList<>();

        for(String line : lines) {
            if (line.contains("Deposit") || line.contains("Withdraw")) {
                String[] splits = line.split(" ");
                commands.add(new Command(splits[1], new BigDecimal(splits[0]), new BigDecimal(splits[2])));
            }
            else if(line.contains("Query")){
                String[] splits = line.split(" ");
                commands.add(new Command(splits[0], new BigDecimal(splits[1]), new BigDecimal(splits[2])));
            }
        }

        return commands;
    }
}
