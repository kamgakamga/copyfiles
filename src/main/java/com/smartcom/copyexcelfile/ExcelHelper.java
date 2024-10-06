package com.smartcom.copyexcelfile;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Slf4j
public class ExcelHelper {

    // Méthode pour charger et modifier le fichier Excel
    public static byte[] cloneAndModifyExcelFile() throws Exception {
        // Charger le fichier depuis le classpath
        ClassPathResource resource = new ClassPathResource("etat_financier.xlsx");
        log.info("la ressource existe ?:{}", resource.exists());
        try (InputStream inputStream = resource.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            // Modifications du fichier (ajout de données, etc.)
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.createRow(1);
            Cell cell = row.createCell(0);
            cell.setCellValue("Modification apportée");

            // Sauvegarder le fichier en mémoire
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    // Charger le fichier Excel depuis le classpath
    public static Workbook loadWorkbookFromClasspath() throws Exception {
        ClassPathResource resource = new ClassPathResource("/etat_financier.xlsx");
        InputStream inputStream = resource.getInputStream();
        return new XSSFWorkbook(inputStream);
    }
}
