import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static java.lang.Integer.parseInt;


public class alpha {
    public static void main(String[] args) {
        getRainfall();
    }

    public static void getRainfall() {
        {
            try {
                double totalRainfall = 0;
                int currentMonth = -1;
                int currentYear = -1;

                Reader in = new FileReader("src/CopperlodeTEST.csv");
                Iterable<CSVRecord> records = CSVFormat.DEFAULT
                        .withHeader("code", "station", "year", "month", "day", "rainfall", "period", "quality")
                        .withSkipHeaderRecord().parse(in);

                for (CSVRecord record : records) {
                    String yearRecord = record.get("year");
                    String monthRecord = record.get("month");
                    String dayRecord = record.get("day");
                    String rainfallAmount = record.get("rainfall");
                    double rainfall;

                    if (rainfallAmount.length() == 0) {
                        rainfall = 0;
                    } else {
                        rainfall = Double.parseDouble(rainfallAmount);
                    }

                    int year = parseInt(yearRecord);
                    int month = parseInt(monthRecord);

                    if (currentMonth == -1) { // get the correct start month
                        currentMonth = month;
                    }

                    if (currentMonth != month){
                        System.out.println("Current Month" + " "+ currentMonth);
                        System.out.println("Total Rain" + " "+ totalRainfall);
                        System.out.println("Current Year" + " " + year + "\n");
                        currentMonth = month;
                        totalRainfall = rainfall;
                        currentYear = year; // track the current year

                    } else {
                        totalRainfall += rainfall;
                    }

//                    System.out.println(totalRainfall);
//                    System.out.println(count);
                }

                // handle any left overs
                System.out.println("Current Month" + " "+ currentMonth);
                System.out.println("Total Rain" + " "+ totalRainfall);
                System.out.println("Current Year" + " " + currentYear + "\n");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


