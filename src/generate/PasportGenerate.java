package generate;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PasportGenerate {
    public static void main(String[] args) {
        writeToCsv();
    }
    public static void writeToCsv() {

        File file = new File("src/data/passport.csv");
        try (FileWriter outputfile = new FileWriter(file)) {

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = {"number"};
            writer.writeNext(header);


            new Random().ints(1000, 1000000, 9999999)
                    .mapToObj(r->new String[]{"BM"+Integer.toString(r)})
                    .forEach(writer::writeNext);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
