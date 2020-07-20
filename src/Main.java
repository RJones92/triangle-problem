
import com.sun.source.tree.IfTree;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Read in the .csv file
        List<Map<String, String>> rowsOfData = CsvReader.readCsv("TestIn.csv");

        //find the lowest origin year and range of years, for the first line of the output.
        int lowestOriginYear = 0;
        int highestDevelopmentYear = 0;
        List<Product> products = new ArrayList<>();

        for (Map<String, String> row : rowsOfData) {
            System.out.println(row);

            //Find and set the lowest origin year
            int originYear = Integer.parseInt(row.get("originYear"));
            if (originYear < lowestOriginYear || lowestOriginYear == 0) {
                lowestOriginYear = originYear;
            }

            //Find and set the highest development year
            int developmentYear = Integer.parseInt(row.get("developmentYear"));
            if (developmentYear > highestDevelopmentYear || developmentYear == 0) {
                highestDevelopmentYear = developmentYear;
            }

            //Create a new Product object for each product available
            String productName = row.get("product");
            if (products.isEmpty()) {
                products.add(new Product(productName));
            } else {
                boolean productAlreadyExits = false;
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getProductName().equals(productName)) {
                        productAlreadyExits = true;
                        break;
                    }
                }
                if (!productAlreadyExits) {
                    products.add(new Product(productName));
                }
            }

            //Add the row of data to the correct Product object
            for (Product product : products) {
                if (product.getProductName().equals(productName)) {
                    product.addProductRow(row);
                    break;
                }
            }

        }


        //Store data for csv output file
        List<List<String>> outputList = new LinkedList<>();

        //first line of the output file
        int rangeOfYears = (highestDevelopmentYear - lowestOriginYear) + 1;
        outputList.add(new ArrayList<>(
                Arrays.asList(
                        Integer.toString(lowestOriginYear),
                        Integer.toString(rangeOfYears))
        ));

        //for each product, calculate the 'triangle' then add as a line in the output file
        for (Product product : products) {
            outputList.add(product.calculateOutput(lowestOriginYear, highestDevelopmentYear));
        }

        //send each product triangle calculation to be written to a csv file
        try{
            CsvWriter.writeCsv(outputList,"outputFile.csv");
        } catch (IOException e){
            System.out.println("IOException found.");
            e.printStackTrace();
        }



    }

}
