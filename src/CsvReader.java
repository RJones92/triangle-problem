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
    
    private static List<Map<String, String>> mapValuesForEachRow(List<List<String>> list){

        List<Map<String, String>> data = new ArrayList<>();

        for (List row : list) {
            Map<String, String> rowMap = new HashMap<>();

            String product = row.get(0).toString();
            String originYear = row.get(1).toString();
            String developmentYear = row.get(2).toString();
            String incrementalValue = row.get(3).toString();

            rowMap.put("product", product);
            rowMap.put("originYear", originYear);
            rowMap.put("developmentYear", developmentYear);
            rowMap.put("incrementalValue", incrementalValue);
            data.add(rowMap);
        }
        return data;
    }

}
