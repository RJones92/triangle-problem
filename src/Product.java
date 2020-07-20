import java.util.*;

public class Product {

    private String productName;
    private List<Map<String, String>> productRows;

    public Product(String productName) {
        this.productName = productName;
        productRows = new ArrayList<>();
    }

    public String getProductName() {
        return this.productName;
    }

    public List<Map<String, String>> getProductRows() {
        return productRows;
    }

    public void addProductRow(Map<String, String> productRow) {
        productRows.add(productRow);
    }

    public List<String> calculateOutput(int startingYear, int lastYear) {

        List<String> outputRow = new LinkedList<>();
        outputRow.add(this.getProductName());

        //set up for loop
        int rowCounter = 0;
        double accumulatedValue = 0;

        //loop through possible originYear's
        for (int originYear = startingYear; originYear < (lastYear + 1); originYear++) {
            Map<String, String> productRow = productRows.get(rowCounter);
            int originYearForRow = Integer.parseInt(productRow.get("originYear"));
            int developmentYearForRow = Integer.parseInt(productRow.get("developmentYear"));

            //loop through possible developmentYears
            for (int developmentYear = originYear; developmentYear < (lastYear + 1); developmentYear++) {

                //if originYear & developmentYear match, accumulate then move onto next row of data
                if ((originYearForRow == originYear) && (developmentYearForRow == developmentYear)) {
                    accumulatedValue += Double.parseDouble(productRow.get("incrementalValue"));

                    //match found so look at next row
                    rowCounter++;
                    if (rowCounter < productRows.size()) {
                        productRow = productRows.get(rowCounter);
                        originYearForRow = Integer.parseInt(productRow.get("originYear"));
                        developmentYearForRow = Integer.parseInt(productRow.get("developmentYear"));
                    }

                }
                //for every developmentYear, even if there's no value, add the accValue
                outputRow.add(Double.toString(accumulatedValue));

            }
            //once we've finished with an originYear, reset the accumulated value
            accumulatedValue = 0;

        }
        System.out.println(outputRow);
        return outputRow;
    }

}
