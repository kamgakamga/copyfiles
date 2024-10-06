package com.smartcom.copyexcelfile;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ExcelService {

    // Dossier où tu veux sauvegarder le fichier sur le serveur
    private static final String SAVE_DIR = "D:\\PROJET_INFO\\SPRING_BOOT\\ADVANCE_IT\\PERSONNEL";

    // Méthode du service pour cloner et modifier l'Excel en fonction des feuilles demandées
    public byte[] getModifiedExcel(List<Integer> sheetIndexes, String export) throws Exception {

        // Charger le fichier Excel depuis le classpath
        Workbook workbook = ExcelHelper.loadWorkbookFromClasspath();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] fileResult ;
        fileResult = buildFileToExport(sheetIndexes, export, workbook, outputStream);
        // Créer un nom de fichier unique pour la sauvegarde
        String filename = "etat_financier_modifie.xlsx";
        Path filePath = Paths.get(SAVE_DIR + filename);

        // Sauvegarder le fichier sur le serveur
        saveFileToServer(workbook, filePath);
        return fileResult;
    }

    private byte[] buildFileToExport(List<Integer> sheetIndexes, String export, Workbook workbook, ByteArrayOutputStream outputStream) throws Exception {
        byte[] fileResult;
        if(!export.equalsIgnoreCase("ALL")){
            // Supprimer les feuilles non demandées
            for (int i = workbook.getNumberOfSheets() - 1; i >= 0; i--) {
                if (!sheetIndexes.contains(i + 1)) {  // +1 car les feuilles sont 1-based pour l'utilisateur
                    workbook.removeSheetAt(i);
                }
            }
            // Sauvegarder le fichier modifié
            workbook.write(outputStream);
            fileResult = outputStream.toByteArray();
        } else {
            fileResult = ExcelHelper.cloneAndModifyExcelFile();
        }
        return fileResult;
    }

    // Méthode pour sauvegarder le fichier sur le serveur
    private void saveFileToServer(Workbook workbook, Path filePath) throws Exception {

        log.info("Chemin est:{}",System.getProperty("user"));
        // Vérifier si le dossier de sauvegarde existe, sinon le créer
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // Sauvegarder le fichier
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            workbook.write(fos);
        }
    }
}
