/**
 * The `Reader` class provides methods to read and parse CSV data into a list of `Human` objects.
 */
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Reader {

    /**
     * Default separator for CSV files.
     */
    private static char separator = ';';

    /**
     * Reads a CSV file and converts its content into a list of `Human` objects.
     *
     * @param csvFilePath The path to the CSV file.
     * @return A list of `Human` objects parsed from the CSV file.
     * @throws FileNotFoundException If the specified CSV file is not found.
     */
    public static List<Human> getListHumansFromCSV(String csvFilePath) {
        try (InputStream in = Reader.class.getClassLoader().getResourceAsStream(csvFilePath);
             CSVReader reader = (in == null) ? null : new CSVReaderBuilder(new InputStreamReader(in))
                     .withCSVParser(new CSVParserBuilder()
                             .withSeparator(separator)
                             .build())
                     .build();
        ) {
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }

            return createHumanList(reader);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a `Human` object from the provided data.
     *
     * @param data A list of key-value pairs representing human attributes.
     * @return A `Human` object created from the provided data.
     */
    public static Human createHuman(ArrayList<MutablePair<String, String>> data) {
        var division = createDivision(data);

        Integer id = null, salary = null;
        String name = null, sex = null;
        LocalDate dateOfBirth = null;

        for (var item : data) {
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

    /**
     * Creates a `Division` object from the provided data.
     *
     * @param data A list of key-value pairs representing division attributes.
     * @return A `Division` object created from the provided data.
     */
    public static Division createDivision(ArrayList<MutablePair<String, String>> data) {
        for (var item : data) {
            if (Objects.equals(item.getLeft(), "Division")) {
                return new Division(item.getRight());
            }
        }
        return null;
    }

    /**
     * Creates a list of `Human` objects from the CSVReader.
     *
     * @param reader The CSVReader containing the CSV data.
     * @return A list of `Human` objects parsed from the CSVReader.
     * @throws IOException If an I/O error occurs while reading the CSV data.
     */
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
