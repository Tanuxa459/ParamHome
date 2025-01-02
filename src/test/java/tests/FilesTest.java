package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FilesTest {

    private ClassLoader cl = FilesTest.class.getClassLoader();

    @Test
    void csvFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("file.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {

            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(
                    new String[] {"Selenide","https://selenide.org"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[] {"JUnit 5","https://junit.org"},
                    data.get(1)
            );
        }
    }
    @DisplayName("Проверка PDF файла из архива")
    @Test
    void checkFileFromZipPDFFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testZip.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {

                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals(205, pdf.numberOfPages);
                }
            }
        }
    }
    @DisplayName("Проверка CVS файла из архива")
    @Test
    void checkFileFromZipCVSFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testZip.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            //boolean pdfFound = false;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".cvs")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(2, data.size());
                    Assertions.assertArrayEquals(
                            new String[] {"Selenide","https://selenide.org"},
                            data.get(0)
                    );
                    Assertions.assertArrayEquals(
                            new String[] {"JUnit 5","https://junit.org"},
                            data.get(1)
                    );

                }
            }
        }
    }
    @DisplayName("Проверка XLS файла из архива")
    @Test
    void checkFileFromZipXLSFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testZip.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xls")) {
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Суммарное количество часов планируемое на штатную по всем разделам"));
                }
            }
        }
    }
}
