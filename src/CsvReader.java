import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvReader {

    public static List<Map<String, String>> readCsv(String filename){
        //Each List<String> will be a row of data
        List<List<String>> rowsFromCsv = new ArrayList<>();
        
        try (BufferedReader incrementalClaimsData = (new BufferedReader(new FileReader(filename)))) {

            //first row are headers. IGNORE THE FIRST ROW
            incrementalClaimsData.readLine();

            String dataRow;
            while ((dataRow = incrementalClaimsData.readLine()) != null) {
                String[] values = dataRow.split(", ");
                rowsFromCsv.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return mapValuesForEachRow(rowsFromCsv);
    }
    
    private static List<Map<String, String>> mapValuesForEachRow(List<List<String>> listToMap){

        List<Map<String, String>> data = new ArrayList<>();

        for (List<String> rowFromList : listToMap) {
            Map<String, String> mappedRow = new HashMap<>();

            String product = rowFromList.get(0);
            String originYear = rowFromList.get(1);
            String developmentYear = rowFromList.get(2);
            String incrementalValue = rowFromList.get(3);

            mappedRow.put("product", product);
            mappedRow.put("originYear", originYear);
            mappedRow.put("developmentYear", developmentYear);
            mappedRow.put("incrementalValue", incrementalValue);
            data.add(mappedRow);
        }
        return data;
    }

}
