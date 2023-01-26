package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTest {

    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadedPdf = $("a[href='junit-user-guide-5.9.2.pdf']").download();
        PDF content = new PDF(downloadedPdf);
        assertThat(content.author).contains("Sam Brannen");
    }

    @Test
    void xlsParseTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("example/sample-xlsx-file.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(1).getCell(1)
                    .getStringCellValue().contains("Dulce"));
        }
    }

    @Test
    void cvsParseTest() throws Exception {
        try (InputStream resource = cl.getResourceAsStream("example/qa_guru.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(resource))) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[1]).contains("lesson");
            assertThat(content.get(1)[0]).contains("Tuchs");
        }
    }
}

