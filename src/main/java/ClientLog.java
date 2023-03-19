import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClientLog {
    private int productNum;
    private int amount;
    private ArrayList<String> log = new ArrayList<>();

    public void log(int productNum, int amount) {
        String s = productNum + "," + amount;
        log.add(s);
    }

    public void exportAsCSV() {
        String s = String.valueOf(LocalDateTime.now());
        log.add(0, s);
        String[] logs = log.toArray(new String[log.size()]);
        try (CSVWriter writer = new CSVWriter(new FileWriter("log.csv", true))) {
            // Записываем запись в файл
            writer.writeNext(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
