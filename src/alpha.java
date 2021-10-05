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
                int currentMonth = 1;
                int count = 0;
                Reader in = new FileReader("src/Copperlode.csv");
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
                    int day = parseInt(dayRecord);
                    int year = parseInt(yearRecord);
                    int month = parseInt(monthRecord);
                    totalRainfall += rainfall;

                    if (currentMonth != month){
                        System.out.println("Current Month" + " "+ currentMonth);
                        System.out.println("Total Rain" + " "+ totalRainfall);
                        System.out.println("Current Year" + " "+ year);
                        currentMonth = month;
                        totalRainfall = 0;
                    }


//                    System.out.println(totalRainfall);
//                    System.out.println(count);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


