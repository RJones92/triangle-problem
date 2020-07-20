import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void writeCsv(List<List<String>> listToOutput, String filename) throws IOException {

        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filename))) {
            for (List<String> rowOfData : listToOutput) {
                csvWriter.append(String.join(",", rowOfData));
                csvWriter.append("\n");
            }
        }

    }

}
