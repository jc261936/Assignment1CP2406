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


                Reader in = new FileReader("Copperlode.csv");
                Iterable<CSVRecord> records = CSVFormat.DEFAULT
                        .withHeader("code", "station", "year", "month", "day", "rainfall", "period", "quality")
                        .withSkipHeaderRecord().parse(in);

                double minRainfall = 0;
                double maxRainfall = 0;
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
                    int day = parseInt(dayRecord);

                    if (currentMonth == -1) {
                        currentMonth = month;
                    }

                    if (currentMonth != month) {
                        System.out.println("Current Month" + " " + currentMonth);
                        System.out.println("Total Rain" + " " + totalRainfall);
                        System.out.println("Current Year" + " " + year);
                        System.out.println("Current Min" + " " + minRainfall);
                        System.out.println("Current Max" + " " + maxRainfall + "\n");
                        currentMonth = month;
                        totalRainfall = rainfall;
                        currentYear = year;
                        minRainfall = 0;
                        maxRainfall = 0;
                        if (rainfall < minRainfall) minRainfall = rainfall;
                        if (rainfall > maxRainfall) maxRainfall = rainfall;



                    } else {
                        totalRainfall += rainfall;
                        if (rainfall < minRainfall) minRainfall = rainfall;
                        if (rainfall > maxRainfall) maxRainfall = rainfall;
                    }


                }
                System.out.println("Current Month: " + currentMonth);
                System.out.println("Total Rain: " + " " + totalRainfall);
                System.out.println("Current Year: " + " " + currentYear);
                System.out.println("Current Min" + " " + minRainfall);
                System.out.println("Current Max" + " " + maxRainfall + "\n");



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeRecord(double totalRainfall, double minRainfall, double maxRainfall, int currentMonth, int year) {
        String newRecord = String.format("%d,%d,%1.2f,%1.2f,%1.2f%s", year, currentMonth,
                totalRainfall, minRainfall, maxRainfall, System.lineSeparator());
        Alpha.TextIO.putf(newRecord);
    }

}

