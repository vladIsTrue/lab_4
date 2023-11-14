/**
 * The `ReaderTest` class contains JUnit tests for the `Reader` class.
 */
package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReaderTest extends TestCase {

    /**
     * Constructs a new `ReaderTest` instance.
     *
     * @param testName The name of the test.
     */
    public ReaderTest(String testName) {
        super(testName);
    }

    /**
     * Creates a test suite containing all the test methods in this class.
     *
     * @return A test suite for this test class.
     */
    public static Test suite() {
        return new TestSuite(ReaderTest.class);
    }

    /**
     * Test case to verify that `getListHumansFromCSV` returns a non-empty list for a valid CSV file.
     */
    public void testGetListHumansFromCSV_ValidFile_ReturnsList() {
        String csvFilePath = "foreign_names.csv";
        assertNotNull(Reader.getListHumansFromCSV(csvFilePath));
        assertFalse(Reader.getListHumansFromCSV(csvFilePath).isEmpty());
    }

    /**
     * Test case to verify that `getListHumansFromCSV` throws a `RuntimeException` for an invalid CSV file.
     */
    public void testGetListHumansFromCSV_InvalidFile_ThrowsFileNotFoundException() {
        String csvFilePath = "invalid_test.csv";
        assertThrows(RuntimeException.class, () -> Reader.getListHumansFromCSV(csvFilePath));
    }

    /**
     * Test case to verify that `createHuman` returns a `Human` object for valid data.
     */
    public void testCreateHuman_ValidData_ReturnsHumanObject() {
        ArrayList<MutablePair<String, String>> data = new ArrayList<>();
        data.add(new MutablePair<>("id", "1"));
        data.add(new MutablePair<>("name", "John Doe"));
        data.add(new MutablePair<>("gender", "Male"));
        data.add(new MutablePair<>("Salary", "50000"));
        data.add(new MutablePair<>("BirtDate", "01.01.1990"));

        assertNotNull(Reader.createHuman(data));
    }

    /**
     * Test case to verify that `createDivision` returns a `Division` object for valid data.
     */
    public void testCreateDivision_ValidData_ReturnsDivisionObject() {
        ArrayList<MutablePair<String, String>> data = new ArrayList<>();
        data.add(new MutablePair<>("Division", "IT"));

        assertNotNull(Reader.createDivision(data));
    }

    /**
     * Test case to verify that `createDivision` returns null for invalid data.
     */
    public void testCreateDivision_InvalidData_ReturnsNull() {
        ArrayList<MutablePair<String, String>> data = new ArrayList<>();
        data.add(new MutablePair<>("InvalidKey", "InvalidValue"));

        assertNull(Reader.createDivision(data));
    }
}
