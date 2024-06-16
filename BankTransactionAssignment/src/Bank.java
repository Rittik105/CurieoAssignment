import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    BigDecimal initialReserve;
    Integer nTransactions;
    Integer nQuery;
    List<Command> commands;

    List<Integer> queryOutputs;

    public Bank(BigDecimal initialReserve, Integer nTransactions, Integer nQuery, List<Command> commands) {
        this.initialReserve = initialReserve;
        this.nTransactions = nTransactions;
        this.nQuery = nQuery;
        this.commands = commands;
        queryOutputs = new ArrayList<>();
    }

    public void runCommands(){
        List<Transaction> transactions = new ArrayList<>();
        Transaction initialTransaction = new Transaction(BigDecimal.valueOf(0),"",BigDecimal.valueOf(0), initialReserve, 0);
        transactions.add(initialTransaction);

        for (Command command : commands) {
            if (command.type().equalsIgnoreCase("Deposit")) {
                Transaction lastTransaction = transactions.get(transactions.size()-1);
                Transaction transaction = new Transaction(command.value1(),
                        "D",
                        command.value2(),
                        lastTransaction.currentReserve().add(command.value2()),
                        lastTransaction.nDeclined());
                transactions.add(transaction);
            } else if (command.type().equalsIgnoreCase("Withdraw")) {
                Transaction lastTransaction = transactions.get(transactions.size()-1);
                Transaction transaction;
                if (lastTransaction.currentReserve().compareTo(command.value2()) >= 0) {
                    transaction = new Transaction(command.value1(),
                            "W",
                            command.value2(),
                            lastTransaction.currentReserve().subtract(command.value2()),
                            lastTransaction.nDeclined());
                } else {
                    transaction = new Transaction(command.value1(),
                            "W",
                            command.value2(),
                            lastTransaction.currentReserve(),
                            lastTransaction.nDeclined() + 1);
                }
                transactions.add(transaction);
            } else if (command.type().equalsIgnoreCase("Query")) {
                Integer newlyAllowed = runQuery(transactions, command.value1(), command.value2());
                queryOutputs.add(newlyAllowed);
            }
        }
    }

    private Integer runQuery(List<Transaction> transactions, BigDecimal start, BigDecimal end) {
        int startIndex = findStartIndex(transactions, start);
        int endIndex = findEndIndex(transactions, end);

        if(startIndex < 1 || endIndex < 1 || startIndex == endIndex){
            return 0;
        }

        BigDecimal currentReserve = transactions.get(startIndex-1).currentReserve();
        int currentDeclined = transactions.get(startIndex-1).nDeclined();

        for(int i = startIndex; i <= endIndex; i++){
            if(transactions.get(i).type().equalsIgnoreCase("D")){
                currentReserve = currentReserve.add(transactions.get(i).amount());
            }
        }

        for(int i = startIndex; i <= endIndex; i++){
            if(transactions.get(i).type().equalsIgnoreCase("W")){
                if(currentReserve.compareTo(transactions.get(i).amount()) >= 0)
                    currentReserve = currentReserve.subtract(transactions.get(i).amount());
                else
                    currentDeclined++;
            }
        }

        return transactions.get(endIndex).nDeclined() - currentDeclined;
    }

    private int findStartIndex(List<Transaction> transactions, BigDecimal time) {
        int start = 0;
        int end = transactions.size() - 1;
        int ans = -1;
        while (start <= end)
        {
            int mid = (start + end) / 2;

            if (transactions.get(mid).timestamp().compareTo(time) < 0)
                start = mid + 1;
            else
            {
                ans = mid;
                end = mid - 1;
            }
        }

        return ans < 1 ? 0 : ans;
    }

    private int findEndIndex(List<Transaction> transactions, BigDecimal time) {
        int start = 0;
        int end = transactions.size() - 1;
        int ans = -1;
        while (start <= end)
        {
            int mid = (start + end) / 2;

            if (transactions.get(mid).timestamp().compareTo(time) >= 0)
                end = mid - 1;
            else
            {
                ans = mid;
                start = mid + 1;
            }
        }

        return ans;
    }

    public void printOutputsOnFile(String fileName) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (int o : queryOutputs) {
                writer.write(String.valueOf(o));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving accounts to file: " + e.getMessage());
        }
    }
    
}