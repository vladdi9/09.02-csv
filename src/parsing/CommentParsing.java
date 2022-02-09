package parsing;

import com.opencsv.CSVWriter;
import model.Comment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommentParsing {
    private static List<Comment> comments = null;

    public static void main(String[] args) {
        String fileName = "src/data/bts_2021_1.csv";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            comments = stream
                    .map(s -> s.split(","))
                    .map(s -> new Comment(s[9], getRandomUser()))
                    .filter(c -> c.getComment().length() < 50)
                    .collect(Collectors.toList());

            for (Comment comment:comments) System.out.println(comment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("src/data/comment.csv");
        try (FileWriter fileWriter = new FileWriter(file)) {
            CSVWriter writer = new CSVWriter(fileWriter);

            // adding header to csv
            String[] header = {"comment", "user_id"};
            writer.writeNext(header);


            comments.stream
                    ()
                    .map(c -> new String[]{c.getComment(), Integer.toString(c.getUser_id())})
                    .forEach(writer::writeNext);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public CommentParsing() throws IOException {
        }

        public static int getRandomUser () {
            //Пользователь
            int min = 1, max = 1000;
            int user_id = (int) ((Math.random() * (max - min)) + min);
            return user_id;
        }

        public static int parseComment (String s){
            int result = 0;
            try {

//System.out.println(s.replaceAll("[^0-9]+",""));
                result = Integer.parseInt(s.replaceAll("[^0-9]+", ""));
            } catch (Exception e) {
            }
            return result;
        }

        public static String parseName (String s){
            return s.replaceAll("[\"]+", "\'");
        }
    }