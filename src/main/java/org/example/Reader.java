package org.example;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Reader {
    public static List<Human> getListHumansFromCSV(String csvFilePath) {
        try (InputStream in = Reader.class.getClassLoader().getResourceAsStream(csvFilePath);
             CSVReader reader = (in == null) ? null : new CSVReaderBuilder(new InputStreamReader(in))
                     .withCSVParser(new CSVParserBuilder()
                             .withSeparator(';')
                             .build())
                     .build();
        ) {
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }

            return createHumanList(reader);

        } catch (IOException e) { throw new RuntimeException(e); }
    }

    public static Human createHuman(ArrayList<MutablePair<String, String>> data) {

        var division = createDivision(data);

        Integer id = null, salary = null;
        String name = null, sex = null;
        LocalDate dateOfBirth = null;

        for(var item : data) {
            if (Objects.equals(item.getLeft(), "id")) {
                id = Integer.parseInt(item.getRight());
            }
            if (Objects.equals(item.getLeft(), "name")) {
                name = item.getRight();
            }
            if (Objects.equals(item.getLeft(), "gender")) {
                sex = item.getRight();
            }
            if (Objects.equals(item.getLeft(), "Salary")) {
                salary = Integer.parseInt(item.getRight());
            }
            if (Objects.equals(item.getLeft(), "BirtDate")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                dateOfBirth = LocalDate.parse(item.getRight(), formatter);
            }
        }

        return new Human(id, name, sex, division, salary, dateOfBirth);
    }

    public static Division createDivision(ArrayList<MutablePair<String, String>> data) {
        for(var item : data) {
            if (Objects.equals(item.getLeft(), "Division")) {
                return new Division(item.getRight());
            }
        }
        return null;
    }

    public static List<Human> createHumanList(CSVReader reader) throws IOException {
        List<Human> listHumans = new LinkedList<>();

        String[] line = reader.readNext();

        ArrayList<MutablePair<String, String>> pairs = new ArrayList<>();
        for (String item : line) {
            pairs.add(new MutablePair<>(item, ""));
        }

        while ((line = reader.readNext()) != null) {
            int i = 0;
            for (var item : pairs) {
                item.setRight(line[i++]);
            }

            listHumans.add(createHuman(pairs));
        }

        return listHumans;
    }
}
