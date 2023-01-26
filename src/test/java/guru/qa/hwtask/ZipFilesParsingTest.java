package guru.qa.hwtask;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFilesParsingTest {
    ClassLoader classLoader = ZipFilesParsingTest.class.getClassLoader();

    @DisplayName("Checking the contents of a zip with a XLSX file")
    @Test
    void zipXlsxParseTest() throws Exception {
        try (
                InputStream resourceAsStream = classLoader.getResourceAsStream("hwtask/FunctionalTesting.zip");
                ZipInputStream zipXlsxInputStream = new ZipInputStream(resourceAsStream)
        ) {
            ZipEntry entryXlsx;
            while ((entryXlsx = zipXlsxInputStream.getNextEntry()) != null) {
                XLS content = new XLS(zipXlsxInputStream);
                assertThat(entryXlsx.getName()).isEqualTo("FunctionalTesting.xlsx");
                assertThat(content.excel.getSheetName(0)).isEqualTo("Задание 1. Форма авторизации");
            }
        }

    }

    @DisplayName("Checking the contents of a zip with a PDF file")
    @Test
    void zipPdfParseTest() throws Exception {
        try (
                InputStream resourceAsStream = classLoader.getResourceAsStream("hwtask/CompatibilityTesting.zip");
                ZipInputStream zipPdfInputStream = new ZipInputStream(resourceAsStream)
        ) {
            ZipEntry entryPdf;
            while ((entryPdf = zipPdfInputStream.getNextEntry()) != null) {
                PDF content = new PDF(zipPdfInputStream);
                assertThat(entryPdf.getName()).isEqualTo("CompatibilityTesting.pdf");
                assertThat(content.text.contains("Описание процесса проведения Compatibility Testing сайта"));
            }
        }
    }

    @DisplayName("Checking the contents of a zip with a CSV file")
    @Test
    void zipCsvParseTest() throws Exception {
        try (
                InputStream resourceAsStream = classLoader.getResourceAsStream("hwtask/smokeTest.zip");
                ZipInputStream zipCsvInputStream = new ZipInputStream(resourceAsStream)
        ) {
            ZipEntry entryCsv;
            while ((entryCsv = zipCsvInputStream.getNextEntry()) != null) {
                CSVReader reader = new CSVReader(new InputStreamReader(zipCsvInputStream));
                List<String[]> content = reader.readAll();
                assertThat(entryCsv.getName()).isEqualTo("smokeTest.csv");
                assertThat(content.get(0)[0])
                        .isEqualTo("Чек лист Smoke Testing  плеера используемого на сайте");
                assertThat(content.get(3)[1])
                        .isEqualTo(" Видео воспроизводится/останавливается по клику на кнопку  \"Смотреть/пауза");
            }
        }
    }
}

