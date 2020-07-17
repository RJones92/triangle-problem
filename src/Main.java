import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //PLAN
        //1. read in CSV file.
        //  1a. error handle for non-int years and values
        //2. test writing CSV file back out again, using the same values
        //3. Do the maths

        //Algorithm
        // accumulatedAmount += developmentYearAmount
        //for each development year we print the accumulatedAmount.

        //4. output the data back into a csv file
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

       List<List<String>> rows = CsvReader.readCsv("TestIn.csv");

        for (List record: rows){
            System.out.println(record);
        }
    }
}
