import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    public static List<List<String>> readCsv(String filename){

        //Each inner-list will be a row of data
        List<List<String>> rows = new ArrayList<>();

        try (BufferedReader incrementalClaimsData = (new BufferedReader(new FileReader("testIn.csv")))) {

            //first row are headers. IGNORE THE FIRST ROW, NOTHING WE HAVE TO WITH IT
            String firstRow = incrementalClaimsData.readLine();
//            String[] headers = firstRow.split(", ");

            //all rows of data
            String dataRow;
            while ((dataRow = incrementalClaimsData.readLine()) != null) {
                String[] values = dataRow.split(", ");
                rows.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }

}
