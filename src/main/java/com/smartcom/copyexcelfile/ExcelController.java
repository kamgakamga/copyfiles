package com.smartcom.copyexcelfile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/download/etat-financier")
    public ResponseEntity<byte[]> downloadEtatFinancier(@RequestParam(name = "sheets", required = false) List<Integer> sheets, @RequestParam(name = "export", defaultValue = "ALL") String export ) throws Exception {
        // Appel du service pour récupérer le fichier Excel modifié
        byte[] excelData = excelService.getModifiedExcel(sheets, export);

        // Préparer la réponse HTTP pour le téléchargement
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", "etat_financier_modifie.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excelData);
    }
}
